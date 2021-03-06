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
		catch(IOException ex) {
			System.out.println("Failed to accept client");
			System.exit(1);
		}

		//setup readers/writers on socket
		System.out.println("Connected by: " + client.getInetAddress());
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		try {
			out = new ObjectOutputStream(client.getOutputStream());
			in = new ObjectInputStream(client.getInputStream());
		}
		catch(IOException ex) {
			System.out.println("Failed to create I/O streams");
			System.exit(1);
		}
		
		//setup 
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));//server input
		String fromServer = "";
		MyObject data = new MyObject();
		System.out.println("Type 'exit' to exit");
		MyObject sendingObj = new MyObject();
		try {
			//receive stuff from socket
			while(!data.myVar.equals("exit") && (data=(MyObject)in.readObject()) != null)
			{
				System.out.println("Client: " + data.myVar);
				fromServer = stdIn.readLine();
				sendingObj.myVar = fromServer;
				out.writeObject(sendingObj);
				if(fromServer.equals("exit")) break;
			}
		}
		catch(SocketException e) {
			System.out.println(e.getMessage());
		}
		catch(ClassNotFoundException e) {e.printStackTrace();}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		

		try {
			out.close();
			in.close();
			client.close();
			ss.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Connection Closed");
	}
}
