package Server;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServerFrame extends JFrame {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 248931008209944416L;
	
	//Atributes
	private JPanel contentPane;
	private JTextField textFieldMessage;
	private JEditorPane editorPaneChat;
	private JScrollPane scrollPaneChat;
	private JList<Object> listUsers;
	private DataOutputStream output;
	private DefaultListModel<Object> listModel;
	private MainThread mainThread;
	

	//Constructors
	public ServerFrame() {
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.WHITE);
		
		JPanel panelBottom = new JPanel();
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(3);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panelTop, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
				.addComponent(panelBottom, GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelTop, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelBottom, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
		);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setBorder(new TitledBorder(null, "Chat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
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
		
		JButton btnSendMessage = new JButton("Enviar");
		btnSendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneChat, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(textFieldMessage, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSendMessage)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(scrollPaneChat, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldMessage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSendMessage))
					.addContainerGap())
		);
		
		editorPaneChat = new JEditorPane();
		editorPaneChat.setEditable(false);
		scrollPaneChat.setViewportView(editorPaneChat);
		panel.setLayout(gl_panel);
		
		JPanel panelLeft = new JPanel();
		splitPane.setLeftComponent(panelLeft);
		panelLeft.setBorder(new TitledBorder(null, "Usurios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnKickUser = new JButton("Expulsar");
		btnKickUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endConnectionWithSelectedUser();
			}
		});
		GroupLayout gl_panelLeft = new GroupLayout(panelLeft);
		gl_panelLeft.setHorizontalGroup(
			gl_panelLeft.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelLeft.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelLeft.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addComponent(btnKickUser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelLeft.setVerticalGroup(
			gl_panelLeft.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelLeft.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnKickUser)
					.addGap(6))
		);
		
		listUsers = new JList<Object>();
		listUsers.setModel(new DefaultListModel<Object>());
		this.listModel = (DefaultListModel<Object>) listUsers.getModel();
		scrollPane.setViewportView(listUsers);
		panelLeft.setLayout(gl_panelLeft);
		
		JButton btnCerrar = new JButton("Cerrar");
		GroupLayout gl_panelBottom = new GroupLayout(panelBottom);
		gl_panelBottom.setHorizontalGroup(
			gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addComponent(btnCerrar)
					.addContainerGap(631, Short.MAX_VALUE))
		);
		gl_panelBottom.setVerticalGroup(
			gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addComponent(btnCerrar)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelBottom.setLayout(gl_panelBottom);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBorder(null);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBorder(null);
		GroupLayout gl_panelTop = new GroupLayout(panelTop);
		gl_panelTop.setHorizontalGroup(
			gl_panelTop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTop.createSequentialGroup()
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(562))
		);
		gl_panelTop.setVerticalGroup(
			gl_panelTop.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelTop.createSequentialGroup()
					.addGroup(gl_panelTop.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
					.addGap(0))
		);
		panelTop.setLayout(gl_panelTop);
		contentPane.setLayout(gl_contentPane);
	}

	
	//Methods
	void sendMessage() {
		
		String message = this.textFieldMessage.getText();
		
		if(message.compareTo("") != 0) {		
			for(SocketThread s: mainThread.getSocketThreads()) {
				try {
					s.getOutput().writeUTF("\n >>SERVIDOR: " + message + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
			this.getEditorPaneChat().setText(editorPaneChat.getText() + "\n >>SERVIDOR: " + message + "\n\n");
			
			this.textFieldMessage.setText(null);
		}
		return;
	}
	
	void sendMessageTo() {
		
		
		
		return;
	}
	
	void endConnectionWithSelectedUser() {
		
		for(int i = 0; i < mainThread.getSocketThreads().size(); ++i) {
			
			System.out.println(this.listUsers.getSelectedValue());
			System.out.println(mainThread.getSocketThreads().get(i).getUserName() + "\n");
			
			if(i == this.listUsers.getSelectedIndex()) {
				
				for(SocketThread s: mainThread.getSocketThreads()) {
					try {
						s.getOutput().writeUTF("\n >>SERVIDOR: " + mainThread.getSocketThreads().get(i).getUserName() + " ha sido expulsado de la sala.\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			
				this.getEditorPaneChat().setText(editorPaneChat.getText() + "\n >>SERVIDOR: " + mainThread.getSocketThreads().get(i).getUserName() + " ha sido expulsado de la sala.\n");
				getListModel().removeElement(mainThread.getSocketThreads().get(i).getUserName());
				mainThread.getSocketThreads().get(i).setExecute(false);
				mainThread.getSocketThreads().remove(i);
			}
		}
		return;
	}


	//Getters & Setters
	public DataOutputStream getOutput() {
		return output;
	}


	public void setOutput(DataOutputStream output) {
		this.output = output;
	}


	public DefaultListModel<Object> getListModel() {
		return listModel;
	}


	public void setListModel(DefaultListModel<Object> listModel) {
		this.listModel = listModel;
	}


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
	
	
	public MainThread getMainThread() {
		return mainThread;
	}


	public void setMainThread(MainThread mainThread) {
		this.mainThread = mainThread;
	}


	public JScrollPane getScrollPaneChat() {
		return scrollPaneChat;
	}


	public void setScrollPaneChat(JScrollPane scrollPaneChat) {
		this.scrollPaneChat = scrollPaneChat;
	}
	
	
}
