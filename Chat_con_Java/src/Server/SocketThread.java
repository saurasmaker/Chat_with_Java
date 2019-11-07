package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
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
		this.serverFrame = serverFrame;
		this.socket = socket;
		this.setSocketThreads(socketThreads);
		
		setBridge();
		
		setName();
		addUser();
		
	}
	
	
	//Methods
	public void run() {
		
		String message = null;
		
		while(true) {
			message = readMessage();
			resendMessage(message);
		}
		
	}
	
	private void setBridge() {
		
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
	
	private void setName(){
		
		try {
			this.setUserName(this.getInput().readUTF());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return;
	}
	
	private void addUser() {

		DefaultListModel<Object> model = serverFrame.getListModel();
		model.addElement(this.userName);
		resendMessage("\n >>SERVIDOR: " + userName + " se ha unido al chat. \n");
		serverFrame.getEditorPaneChat().setText(serverFrame.getEditorPaneChat().getText() + "\n >>SERVIDOR: " + userName + " se ha unido al chat. \n");
		serverFrame.repaint();
		
		return;
	}
	
	private String readMessage() {
		
		String message = null;
		
		try {
			message = this.getInput().readUTF();
			serverFrame.getEditorPaneChat().setText(serverFrame.getEditorPaneChat().getText() + message + " \n");
			serverFrame.getScrollPaneChat().getVerticalScrollBar().setValue(serverFrame.getScrollPaneChat().getVerticalScrollBar().getMaximum());
			serverFrame.repaint();
		} catch (IOException e) {
			try {
				resendMessage("\n >>SERVIDOR: " + userName + " ha abandonado el chat. \n\n");
				serverFrame.getEditorPaneChat().setText(serverFrame.getEditorPaneChat().getText() + "\n >>SERVIDOR: " + userName + " ha abandonado el chat. \n");
				socket.close();
				//socketThreads.get(this);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return message;
	}
	
	private void resendMessage(String message){
		
		for(SocketThread s: socketThreads) {
			try {
				s.getOutput().writeUTF(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
