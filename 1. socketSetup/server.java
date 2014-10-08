import java.net.*;
import java.io.*;

public class server {
	public static void main(String[] args) {
		final int PORT = 3333;

		ServerSocket ss = null;
		try {
			ss = new ServerSocket(PORT);	//create, bind, and listen on PORT
			System.out.println("Listening on PORT " + PORT);
		}
		catch(IOException ex) {
			System.out.println("Error can't connect to PORT " + PORT);
			System.exit(1);
		}

		Socket client = null;
		try {
			client = ss.accept();	//accept the first connection
			System.out.println("Accepted client socket");
		}
		catch (IOException ex) {
			System.out.println("Failed to accept client");
			System.exit(1);
		}

		System.out.println("Connected by: " + client.getInetAddress());
		
		try {
			ss.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Connection Closed");
	}
}