import java.net.*;
import java.util.ArrayList;
import java.io.*;

import javax.swing.JLabel;

public class GUIChatReader extends Thread {
	private Socket socket = null;
	private JLabel display = null;
	private String friendString = "Client";
	public GUIChatReader(Socket s, JLabel display) {
		this.socket = s;
		this.display = display;
	}
	public GUIChatReader(Socket s, JLabel display, boolean isFriendAServer) {
		this.socket = s;
		this.display = display;
		if(!isFriendAServer) {
			friendString = "Server";
		}
	}
	@Override
	public void run() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String fromClient;
			try {
				while((fromClient=in.readLine()) != null && !interrupted()) {
					if(display.getText().equals("")) {
						display.setText("<html>" + friendString + ": " + fromClient + "</html>");
					}
					else {
						display.setText(display.getText().substring(0,display.getText().length()-7) +"<br>" + friendString + ": " + fromClient + "</html>");
					}
				}
			}
			catch(SocketException e) {
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		finally {
			try {
				in.close();
				System.out.println("ChatReader closed");
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
		}
	}

}
