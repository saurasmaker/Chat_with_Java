package Client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JEditorPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JTextField textFieldMessage;
	private JEditorPane editorPaneChat;
	private ClientThread clientThread;
	private JScrollPane scrollPaneChat;
	
	//Constructor
	public ClientFrame() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setBorder(new TitledBorder(null, "Chat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		scrollPaneChat = new JScrollPane();
		scrollPaneChat.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		textFieldMessage = new JTextField();
		textFieldMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					sendMessage();
			}
		});
		textFieldMessage.setColumns(10);
		
		JButton buttonSendMessage = new JButton("Enviar");
		buttonSendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		GroupLayout gl_panelCenter = new GroupLayout(panelCenter);
		gl_panelCenter.setHorizontalGroup(
			gl_panelCenter.createParallelGroup(Alignment.LEADING)
				.addGap(0, 550, Short.MAX_VALUE)
				.addGroup(gl_panelCenter.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneChat, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
						.addGroup(gl_panelCenter.createSequentialGroup()
							.addComponent(textFieldMessage, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buttonSendMessage)))
					.addContainerGap())
		);
		gl_panelCenter.setVerticalGroup(
			gl_panelCenter.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 389, Short.MAX_VALUE)
				.addGroup(gl_panelCenter.createSequentialGroup()
					.addComponent(scrollPaneChat, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldMessage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonSendMessage))
					.addContainerGap())
		);
		
		editorPaneChat = new JEditorPane();
		scrollPaneChat.setViewportView(editorPaneChat);
		panelCenter.setLayout(gl_panelCenter);
		
		JPanel panelBottom = new JPanel();
		
		JButton buttonClose = new JButton("Cerrar");
		GroupLayout gl_panelBottom = new GroupLayout(panelBottom);
		gl_panelBottom.setHorizontalGroup(
			gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGap(0, 694, Short.MAX_VALUE)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addComponent(buttonClose)
					.addContainerGap(631, Short.MAX_VALUE))
		);
		gl_panelBottom.setVerticalGroup(
			gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGap(0, 28, Short.MAX_VALUE)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addComponent(buttonClose)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelBottom.setLayout(gl_panelBottom);
		
		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.WHITE);
		
		JButton button_2 = new JButton("New button");
		button_2.setBorder(null);
		button_2.setBackground(Color.WHITE);
		
		JButton button_3 = new JButton("New button");
		button_3.setBorder(null);
		button_3.setBackground(Color.WHITE);
		GroupLayout gl_panelTop = new GroupLayout(panelTop);
		gl_panelTop.setHorizontalGroup(
			gl_panelTop.createParallelGroup(Alignment.LEADING)
				.addGap(0, 694, Short.MAX_VALUE)
				.addGroup(gl_panelTop.createSequentialGroup()
					.addComponent(button_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(562))
		);
		gl_panelTop.setVerticalGroup(
			gl_panelTop.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 20, Short.MAX_VALUE)
				.addGroup(gl_panelTop.createSequentialGroup()
					.addGroup(gl_panelTop.createParallelGroup(Alignment.TRAILING)
						.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
						.addComponent(button_2, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
					.addGap(0))
		);
		panelTop.setLayout(gl_panelTop);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panelBottom, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
				.addComponent(panelTop, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
				.addComponent(panelCenter, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelTop, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelCenter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panelBottom, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	//Methods
	void sendMessage() {
		
		String message;
		
		if((message = this.textFieldMessage.getText())!= null) {
			try {
				clientThread.getOutput().writeUTF(clientThread.getUserName() + ": " + message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			this.textFieldMessage.setText(null);
		}
		
		return;
	}

	
	//Getters & Setters
	public JTextField getTextFieldMessage() {
		return textFieldMessage;
	}

	public void setTextFieldMessage(JTextField textFieldMessage) {
		this.textFieldMessage = textFieldMessage;
	}

	public JEditorPane getEditorPaneChat() {
		return editorPaneChat;
	}

	public void setEditorPaneChat(JEditorPane editorPaneChat) {
		this.editorPaneChat = editorPaneChat;
	}

	public ClientThread getClientThread() {
		return clientThread;
	}

	public void setClientThread(ClientThread clientThread) {
		this.clientThread = clientThread;
	}

	public JScrollPane getScrollPaneChat() {
		return scrollPaneChat;
	}

	public void setScrollPaneChat(JScrollPane scrollPaneChat) {
		this.scrollPaneChat = scrollPaneChat;
	}

	
}
