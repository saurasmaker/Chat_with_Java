package Server;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ServerFrame extends JFrame {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 248931008209944416L;
	
	//Atributes
	private JPanel contentPane;

	
	//Constructors
	public ServerFrame() {
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
