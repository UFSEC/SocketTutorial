import java.net.*;
import java.util.ArrayList;
import java.io.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUIChatClient extends JFrame {
	private static final long serialVersionUID = 1L;
	private final String serverIP = "localhost";
	private final int port = 3333;
	private Socket client = null;
	private PrintWriter out = null;
	private GUIChatReader cr = null;
	private JTextField userMessage = null; 
	private JLabel clientMessage = null;

	public static void main(String[] args) {		
		GUIChatClient cc = new GUIChatClient();
		//cs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cc.setVisible(true);
		cc.startClient();
	}

	private class MessageListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			out.println(userMessage.getText());
			if(clientMessage.getText().equals("")) {
				clientMessage.setText("<html>Client: " + userMessage.getText() + "</html>");
			}
			else {
				clientMessage.setText(clientMessage.getText().substring(0,clientMessage.getText().length()-7) +"<br>Client: " + userMessage.getText() + "</html>");
			}
			userMessage.setText("");
		}
	}

	public GUIChatClient() {
		setTitle("ChatClient");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeSockets();
			}
		});
		GridBagConstraints grid = new GridBagConstraints();
		getContentPane().setLayout(new GridBagLayout());
		
		clientMessage = new JLabel();
		clientMessage.setPreferredSize(new Dimension(250,250));
		clientMessage.setVerticalAlignment(JLabel.TOP);
		grid.gridx = 0;
		grid.gridy = 0;
		JScrollPane scrollPane = new JScrollPane(clientMessage);
		getContentPane().add(scrollPane, grid);

		userMessage = new JTextField(10);
		grid.gridx = 0;
		grid.gridy = 1;
		getContentPane().add(userMessage,grid);
		userMessage.addActionListener(new MessageListener());

		JButton send = new JButton();
		send.setText("Send");
		grid.gridx = 0;
		grid.gridy = 2;
		getContentPane().add(send, grid);
		send.addActionListener(new MessageListener());

		pack();
	}

	public void startClient() {
		//get client, setup I/O streams to/from client
		try {
			client = new Socket(serverIP, port);
			System.out.println("Accepted client socket");
			out = new PrintWriter(client.getOutputStream(), true);
			cr = new GUIChatReader(client, clientMessage, false);
			cr.start();
			System.out.println("Created I/O streams");
		} catch (IOException ex) {
			System.out.println("Failed to accept client OR create I/O streams");
			System.exit(1);
		}
	}

	public void closeSockets() {
		if(client != null) {
			try {
				client.close();
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
		}

		System.out.println("Client Exited");
		System.exit(0);
	}
}
