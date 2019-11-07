package Client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ClientFrame extends JFrame {

	/**
	 * This is the frame of the client
	 * We will be able to send messages
	 * from this frame to the server
	 * the server will re-send the messages
	 * to the others clients 
	 */
	
	private static final long serialVersionUID = 385722919034680835L;
	
	//Atributes
	private JPanel contentPane;

	
	//Constructor
	public ClientFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	//Methods
	void sendMessage() {
		
		return;
	}
	
	//Getters & Setters

}
