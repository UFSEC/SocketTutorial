import java.io.*;
import java.net.*;

public class client {
	public static void main(String[] args) throws IOException {
		final String HOST = "localhost";
		final int PORT = 3333;
		
		//connect to server, setup I/O streams to/from server
		Socket s = null;
		try {
			s = new Socket(HOST, PORT);	//create socket and connect to server
			System.out.println("Connected on PORT " + PORT);
		}
		catch (UnknownHostException ex) {
			System.err.println("Couldn't connect to the server");
			System.exit(1);
		}
		catch (IOException ex) {
			System.err.println("Couldn't get I/O for the connection");
			System.exit(1);
		}
		
		System.out.println("Connected");

		//setup readers/writers on the socket
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		try {
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());
		}
		catch (IOException ex) {
			System.err.println("Couldn't get I/O for the connection");
			System.exit(1);
		}
		
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));//user input
		String fromUser = "";
		System.out.println("Type 'exit' to exit");
		MyObject sendingObj = new MyObject();
		try {
			//send stuff on socket (and receive stuff too)
			while(!fromUser.equals("exit") && (fromUser = stdIn.readLine()) != null) {
			  sendingObj.myVar = fromUser;
				out.writeObject(sendingObj);
				MyObject rcvObj = (MyObject)in.readObject();
				System.out.println(" Response: " + rcvObj.myVar);
			}
		}
		catch(SocketException e) {
			System.out.println(e.getMessage());
		}
		catch(ClassNotFoundException e) {e.printStackTrace();}
		
		try {
			stdIn.close();
			out.close();
			in.close();
			s.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Connection Closed");
	}
}
