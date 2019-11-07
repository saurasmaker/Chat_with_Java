package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MainThread {
	
	//Finals
	static final String error_title = "Error de conexión de servidor";
	static final String errorPort_message = "Error al conectar el servidor con el puerto ";
	static final String errorConnection_message = "Error de conexión cliente servidor.";
	final int port = 5000;
	
	//Atributes
	private ArrayList<SocketThread> socketThreads;
	private ServerSocket serverSocket;
	private ServerFrame serverFrame;
	private Socket socket;
	
	
	//Constructors
	public MainThread() {
		this.socketThreads = new ArrayList<SocketThread>();
		try {
			this.serverSocket = new ServerSocket(this.port);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, MainThread.errorPort_message + this.port + "\n" + e.getLocalizedMessage(), /*Title*/ MainThread.error_title, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	//Methods
	void run() {
		
		while(true) {
			addNewConnection();
		}
				
	}
	
	private void addNewConnection() {
		
		try {
			this.socket = this.serverSocket.accept();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, MainThread.errorConnection_message + "\n" + e.getLocalizedMessage(), /*Title*/ MainThread.error_title, JOptionPane.ERROR_MESSAGE);
		}
		
		this.socketThreads.add(new SocketThread(this.socket,this.socketThreads, this.serverFrame));
		this.socketThreads.get(this.socketThreads.size()-1).start();
		
		return;
	}


	
	//Getters & Setters
	public ServerFrame getServerFrame() {
		return serverFrame;
	}


	public void setServerFrame(ServerFrame serverFrame) {
		this.serverFrame = serverFrame;
	}
}
