package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ClientThread extends Thread{

	//Atributes
	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	private String userName;
	
	//Constructors
	public ClientThread() {
		
	}
	
	
	//Methods
	void setCommunication() {
		
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
		
		this.setUserName(JOptionPane.showInputDialog(null, "Introduzca su nombre de USUARIO antes de comenzar: "));
		
		return;
	}
	
	void sendName() {
		try {
			output.writeUTF(this.getUserName());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
}
