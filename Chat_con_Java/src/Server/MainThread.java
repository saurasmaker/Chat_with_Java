package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MainThread {
	
	//Finals
	final String errorConnection = "Error al conectar el servidor con el puerto ";
	final int port = 5000;
	
	//Atributes
	ArrayList<SocketThread> socketThreads;
	ServerSocket serverSocket;
	
	
	
	//Constructors
	public MainThread() {
		socketThreads = new ArrayList<SocketThread>();
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,  errorConnection + port + "\n" + e.getLocalizedMessage(), /*Title*/ "Error de conexión de servidor", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	//Methods
	void run() {
		
		
		
		
		return;
	}
}
