package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import Server.MainThread;

public class ClientThread extends Thread{

	//Statics
	
	static final String error_title = "Error de conexión de servidor";
	static final String errorPort_message = "Error al conectar el servidor con el puerto ";
	static final String errorConnection_message = "Error al intentar conectar con el servidor.";
	
	private String host = "192.168.6.9";/*"192.168.1.40"*/;
	final int port = 5000;
	
	//Atributes
	private Boolean paused = false, suspended = false;
	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	private String userName;
	private ClientFrame clientFrame;
	
	//Constructors
	public ClientThread() {
		setComunication();
		setName();
	}
	
	
	//Methods
	public void run() {
		
		clientFrame.setClientThread(this);
		
		String message = null;
		
		while(true){
			try {
				synchronized(this){
					while(this.isPaused()) 
						wait();
					
					if(this.isSuspended())
						break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			message = readMessage();
			showMessage(message);
		}
		
	}
	void setComunication() {
		
		try {
			this.socket = new Socket(host,port);
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, ClientThread.errorConnection_message + "\n" + e.getMessage(), /*Title*/ ClientThread.error_title, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ClientThread.errorConnection_message + "\n" + e.getMessage(), /*Title*/ ClientThread.error_title, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		try {
			this.setOutput(new DataOutputStream(socket.getOutputStream()));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ClientThread.errorConnection_message + "\n" + e.getMessage(), /*Title*/ ClientThread.error_title, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		try {
			this.setInput(new DataInputStream(socket.getInputStream()));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ClientThread.errorConnection_message + "\n" + e.getMessage(), /*Title*/ ClientThread.error_title, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} 
			
		return;
	}
	
	void setName(){
		
		this.setUserName(JOptionPane.showInputDialog(null, "Introduzca su nombre de USUARIO antes de comenzar: "));
		//clientFrame.setTitle(clientFrame.getTitle()+ ": " + getUserName());
		try {
			output.writeUTF(this.getUserName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return;
	}
	
	private String readMessage() {
		
		String message = null;
		
		try {
			message = this.getInput().readUTF();
			clientFrame.getEditorPaneChat().setText(clientFrame.getEditorPaneChat().getText() + message + "\n");
			clientFrame.getScrollPaneChat().getVerticalScrollBar().setValue(clientFrame.getScrollPaneChat().getVerticalScrollBar().getMaximum());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Se ha perdido la conexión con el servidor.\n" + e.getMessage(), /*Title*/ ClientThread.error_title, JOptionPane.ERROR_MESSAGE);
			setSuspended(true);
			this.clientFrame.dispose();
			e.printStackTrace();
		}
		
		return message;
	}
	
	private void showMessage(String message) {
		
		/*
		 * Código para mostrar mensaje en la ventana
		 * del cliente. 
		 */
		
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


		public ClientFrame getClientFrame() {
			return clientFrame;
		}


		public void setClientFrame(ClientFrame clientFrame) {
			this.clientFrame = clientFrame;
		}

		public Boolean isSuspended() {
			return suspended;
		}


		public void setSuspended(Boolean suspended) {
			this.suspended = suspended;
		}


		public Boolean isPaused() {
			return paused;
		}


		public void setPaused(Boolean paused) {
			this.paused = paused;
		}

}
