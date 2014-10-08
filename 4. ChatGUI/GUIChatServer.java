import java.net.*;
import java.util.ArrayList;
import java.io.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUIChatServer extends JFrame {
	private static final long serialVersionUID = 1L;
	private ServerSocket server = null;
	private Socket client = null;
	private PrintWriter out = null;
	private GUIChatReader cr = null;
	private JTextField userMessage = null;
	private JLabel clientMessage = null;

	public static void main(String[] args) {		
		GUIChatServer cs = new GUIChatServer();
		//cs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cs.setVisible(true);
		cs.startServer();
	}

	private class MessageListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(client != null) {
				out.println(userMessage.getText());
				if(clientMessage.getText().equals("")) {
					clientMessage.setText("<html>Server: " + userMessage.getText() + "</html>");
				}
				else {
					clientMessage.setText(clientMessage.getText().substring(0,clientMessage.getText().length()-7) +"<br>Server: " + userMessage.getText() + "</html>");
				}
				userMessage.setText("");
			}
		}
	}

	public GUIChatServer() {
		setTitle("ChatServer");
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

	public void startServer() {
		final int port = 3333;
		//setup serversocket
		try {
			server = new ServerSocket(port);
			System.out.println("Listening on port " + port);
		}
		catch(IOException ex) {
			System.out.println("Error can't connect to port " + port);
			System.exit(1);
		}

		//get client, setup I/O streams to/from client
		try {
			client = server.accept();
			System.out.println("Accepted client socket");
			out = new PrintWriter(client.getOutputStream(), true);
			cr = new GUIChatReader(client, clientMessage);
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

		if(server != null) {
			try {
				server.close();
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
		}

		System.out.println("Server Exited");
		System.exit(0);
	}
}