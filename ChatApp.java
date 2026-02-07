// -------------------------------------------------
// include required libraries to ensure program runs
 import java.net.*;
 import java.io.*;
 import java.util.Scanner;
// -------------------------------------------------

// -------------------------------------------------
// Server side - Create a class for a ChatServer
// -------------------------------------------------
public class ChatApp{
	static String StartServer(int port){

		// Set a timeout value that defines when the server will stop waiting for connections
		int timeout=20000;
		// Try to create a ServerSocket that opens a port required to wait for connections. Or print an error if it fails.
		try{
			ServerSocket demoServer = new ServerSocket(port);
			demoServer.setSoTimeout(timeout);

			// Check if the ServerSocket created earlier is active
			if(demoServer.isBound()){
				System.out.println("Timeout in " + (timeout/1000) +
						" seconds before giving up");
				// Try to Create the required client Socket accept() to wait for connections
				// or fail with an error message
				try{
					Socket demoClient = demoServer.accept(); 
					System.out.println("Client Connected: " + demoClient);
					/* 
					  link to Java Reference URLs:
					  InputStreamReader: https://docs.oracle.com/javase/8/docs/api/java/io/InputStreamReader.html
					  BufferedReader: https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html 
					*/

					// Create BufferedReader from the client socket connection that will read sent data 
					BufferedReader ClientInput = new BufferedReader(new InputStreamReader(demoClient.getInputStream()));

					// cycle through data to read all the input from client connection 
					String line;
					while((line = ClientInput.readLine()) != null){
						System.out.println("Client input: " + line);
					}
				        // Close the client Connection
					demoServer.close();

				// Continuing from the 'Try' statement earlier. 'Catch' any connection errors and print a message.
				} catch (IOException e){
					return "Client connection failed: " + e + "\n";
				}
			}
		}
		// Continue from the first 'Try' statement. This 'catch' will error if unable to create the ServerSocket.
		catch(IOException e){
			// return error message from stack trace if failure to connect occurs.
			return "Server Failed to create SockerServer: " + e + " on port:" + port +"\n";
		}
		// print the statement informing the user the connect has closed
		return "Closing Connection on Port: " + port;

	}

// -------------------------------------------------
// 			 Main 
// -------------------------------------------------
	public static void main(String[] args){

		 // ServerSocket class - documentation:
		 // https://docs.oracle.com/javase/8/docs/api/java/net/ServerSocket.html

		 // Prompt the user to enter the server's port number
		 int PORT;
		 String HOST="localhost";
		 
		 // Create a Scanner, then Prompt the user to enter the server's port number
		 System.out.print("Enter the port number for the server: ");
		 Scanner input = new Scanner(System.in);
		 PORT = input.nextInt();
		 // If the port entered is greater than 9999 or less than 0, return an error. Otherwise continue
		 if(PORT >9999|| PORT <0){
		 	// print the suggested port range 
			 System.out.println("Port must be in the range from 0 to 9999 ");
		 } else {
			// Call Method to start Server at selected port
			 System.out.println(StartServer(PORT));
		 }
	}
}
