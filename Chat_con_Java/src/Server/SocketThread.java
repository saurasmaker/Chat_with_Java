package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SocketThread extends Thread{

	//Atributes
	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	private String userName;
	private ArrayList<SocketThread> socketThreads;
	private ServerFrame serverFrame;
	
	
	//Constructors
	public SocketThread(Socket socket, ArrayList<SocketThread> socketThreads, ServerFrame serverFrame){
		
		this.socket = socket;
		this.setSocketThreads(socketThreads);
		
		setBridge();
		
		setName();
	
	}
	
	
	//Methods
	public void run() {
		
		return;
	}
	
	void setBridge() {
		
		try {
			this.setOutput(new DataOutputStream(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			this.setInput(new DataInputStream(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		} 
			
		return;
	}
	
	void setName(){
		
		try {
			this.setUserName(this.getInput().readUTF());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return;
	}

	//Getters & Setters
	public DataInputStream getInput() {
		return input;
	}


	public void setInput(DataInputStream input) {
		this.input = input;
	}


	public DataOutputStream getOutput() {
		return output;
	}


	public void setOutput(DataOutputStream output) {
		this.output = output;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public ArrayList<SocketThread> getSocketThreads() {
		return socketThreads;
	}


	public void setSocketThreads(ArrayList<SocketThread> socketThreads) {
		this.socketThreads = socketThreads;
	}


	public ServerFrame getServerFrame() {
		return serverFrame;
	}


	public void setServerFrame(ServerFrame serverFrame) {
		this.serverFrame = serverFrame;
	}
}
