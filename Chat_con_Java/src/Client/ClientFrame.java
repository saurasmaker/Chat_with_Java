package Client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import ExternClasses.ComponentResizer;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JEditorPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Window.Type;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

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
	
	private int xMouse, yMouse;
	
	//Constructor
	public ClientFrame() {
		
		//Basic
		setTitle("Client");
		setLocationRelativeTo(null);		
        this.setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 372);
		ComponentResizer cr = new ComponentResizer();
		cr.registerComponent(this);
		cr.setMinimumSize(this.getSize());
        cr.setSnapSize(new Dimension(10, 10));
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(new Color(51, 51, 51));
		panelCenter.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chat", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 153, 153)));
		
		scrollPaneChat = new JScrollPane();
		scrollPaneChat.setBackground(new Color(51, 51, 51));
		scrollPaneChat.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		textFieldMessage = new JTextField();
		textFieldMessage.setCaretColor(Color.WHITE);
		textFieldMessage.setBackground(new Color(0, 0, 0));
		textFieldMessage.setForeground(new Color(255, 255, 255));
		textFieldMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					sendMessage();
			}
		});
		textFieldMessage.setColumns(10);
		
		JButton buttonSendMessage = new JButton("Enviar");
		buttonSendMessage.setForeground(Color.WHITE);
		buttonSendMessage.setBorder(new LineBorder(new Color(0, 0, 0)));
		buttonSendMessage.setBackground(new Color(102, 0, 0));
		buttonSendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		GroupLayout gl_panelCenter = new GroupLayout(panelCenter);
		gl_panelCenter.setHorizontalGroup(
			gl_panelCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCenter.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneChat)
						.addGroup(Alignment.TRAILING, gl_panelCenter.createSequentialGroup()
							.addComponent(textFieldMessage, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buttonSendMessage, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panelCenter.setVerticalGroup(
			gl_panelCenter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCenter.createSequentialGroup()
					.addComponent(scrollPaneChat, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldMessage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonSendMessage, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		editorPaneChat = new JEditorPane();
		editorPaneChat.setSelectionColor(new Color(0, 100, 0));
		editorPaneChat.setCaretColor(Color.WHITE);
		editorPaneChat.setBackground(new Color(0, 0, 0));
		editorPaneChat.setForeground(new Color(255, 255, 255));
		editorPaneChat.setEditable(false);
		scrollPaneChat.setViewportView(editorPaneChat);
		panelCenter.setLayout(gl_panelCenter);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(new Color(51, 0, 0));
		GroupLayout gl_panelBottom = new GroupLayout(panelBottom);
		gl_panelBottom.setHorizontalGroup(
			gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGap(0, 743, Short.MAX_VALUE)
		);
		gl_panelBottom.setVerticalGroup(
			gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGap(0, 28, Short.MAX_VALUE)
		);
		panelBottom.setLayout(gl_panelBottom);
		
		JPanel panelTop = new JPanel();
		panelTop.setBackground(new Color(51, 51, 51));
		
		JButton btnProfile = new JButton("  Profile  ");
		btnProfile.setForeground(new Color(255, 255, 255));
		btnProfile.setBorder(null);
		btnProfile.setBackground(new Color(51, 51, 51));
		
		JButton buttonSelectTheme = new JButton("  Theme  ");
		buttonSelectTheme.setForeground(new Color(255, 255, 255));
		buttonSelectTheme.setBorder(null);
		buttonSelectTheme.setBackground(new Color(51, 51, 51));
		
		JButton btnHelp = new JButton("  Help  ");
		btnHelp.setForeground(new Color(255, 255, 255));
		btnHelp.setBorder(null);
		btnHelp.setBackground(new Color(51, 51, 51));
		GroupLayout gl_panelTop = new GroupLayout(panelTop);
		gl_panelTop.setHorizontalGroup(
			gl_panelTop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTop.createSequentialGroup()
					.addComponent(btnProfile)
					.addGap(2)
					.addComponent(buttonSelectTheme)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnHelp)
					.addGap(646))
		);
		gl_panelTop.setVerticalGroup(
			gl_panelTop.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelTop.createSequentialGroup()
					.addGroup(gl_panelTop.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnProfile, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
						.addComponent(buttonSelectTheme, GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
						.addComponent(btnHelp, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(0))
		);
		panelTop.setLayout(gl_panelTop);
		
		JPanel panelHead = new JPanel();
		panelHead.setBackground(new Color(51, 0, 0));
		panelHead.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				//if (e.getButton() == MouseEvent.BUTTON1){
					Point point = MouseInfo.getPointerInfo().getLocation();
					setLocation(point.x - xMouse, point.y - yMouse);
				//}
			}
		});
		panelHead.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//if (e.getButton() == MouseEvent.BUTTON1){
					xMouse = e.getX();
	                yMouse = e.getY();  
				//}
                /*
                else if (e.getButton() == MouseEvent.BUTTON2){
                    System.out.println("Middle button clicked");
                } 
                else if (e.getButton() == MouseEvent.BUTTON3) {
                    System.out.println("Right button clicked");
                } 
                */   
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panelCenter, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
				.addComponent(panelBottom, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
				.addComponent(panelTop, GroupLayout.PREFERRED_SIZE, 534, Short.MAX_VALUE)
				.addComponent(panelHead, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelHead, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelTop, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelCenter, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelBottom, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
		);
		
		JLabel lblClose = new JLabel("Close");
		lblClose.setForeground(new Color(255, 255, 255));
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					clientThread.setSuspended(true);
					sayGoodbye();
					dispose();
				}
			}
		});
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblMax = new JLabel("Max");
		lblMax.setForeground(new Color(255, 255, 255));
		lblMax.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1){
					if(getExtendedState() == JFrame.MAXIMIZED_BOTH)
						setExtendedState(JFrame.NORMAL);
					else
						setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
			}
		});
		lblMax.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblMin = new JLabel("Min");
		lblMin.setForeground(new Color(255, 255, 255));
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)
					setExtendedState(JFrame.ICONIFIED);
			}
		});
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblIcon = new JLabel("Icon");
		lblIcon.setForeground(new Color(255, 255, 255));
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panelHead = new GroupLayout(panelHead);
		gl_panelHead.setHorizontalGroup(
			gl_panelHead.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHead.createSequentialGroup()
					.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 585, Short.MAX_VALUE)
					.addComponent(lblMin, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMax, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblClose, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
		);
		gl_panelHead.setVerticalGroup(
			gl_panelHead.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHead.createSequentialGroup()
					.addGroup(gl_panelHead.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHead.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblClose, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblMax, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
						.addComponent(lblMin, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panelHead.setLayout(gl_panelHead);
		contentPane.setLayout(gl_contentPane);
	}
	
	//Methods
	void sendMessage() {
		
		String message = this.getTextFieldMessage().getText();
		
		if(message.compareTo("") != 0) {
			try {
				clientThread.getOutput().writeUTF(clientThread.getUserName() + ": " + message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		this.textFieldMessage.setText(null);
		
		return;
	}
	
	void sayGoodbye() {
		
		try {
				clientThread.getOutput().writeUTF("\n >>SERVIDOR: " + clientThread.getUserName() + " ha abandonado el chat.\n\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			this.textFieldMessage.setText(null);
			
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
