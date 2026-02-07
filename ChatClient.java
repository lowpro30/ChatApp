
// -------------------------------------------------
// libraries required
 import java.net.*;
 import java.io.*;
 import java.util.Scanner;
// -------------------------------------------------

// -------------------------------------------------
// Client side - Create a class for a ChatClient
// -------------------------------------------------

public class ChatClient{
	static  String StartClient(String host, int port){
		// Create a Client connection Socket. Provide message indicating success or fail. 
		try{
			Socket clientSocket = new Socket(host,port);
			System.out.println("Attempting to connect to Server: " + host 
					+ " on port: " 
					+ port 
					+ "\n");
			if(clientSocket.isBound()){
				System.out.println("Connected to server: " + host 
					+ "\n" 
				        + "Port: " 
					+ port + "\n" );

				// Create BufferedWriter based on client socket to 'wrap' data and send output to server
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

				// Create BufferedReader input to read input from user - only necessary for two-way communication.
				// BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
				// Create a Scanner named 'console' that will read user input from the console.
				Scanner console = new Scanner(System.in);

				// While loop - read user input until the user sends a blank line
				while(true){
					System.out.println("Client: ");
					String line = console.nextLine();
					
					// keep sending user input until the user sends an empty line
					// skips this block until it is true or the input is 'empty'
					if(line.isEmpty()){
						break;
					}

					// write each console line to the server and flush the buffer
					out.write(line);
					out.newLine();
					out.flush();
				}
				// While loop has completed due to a blank line. Return a closing connection message.
				return "Closing Connection to Server: " + host + " on port: " + port + "\n";

			}
			// Return an error message if there is a connection issue.
		}catch(IOException e){
			System.out.println("Failed to connect to Server: " + host 
					+ "\n" 
				        + "Port: " 
					+ port + "\n" 
					+ e + "\n");

		}
		// If all other options have failed. Provide the message stating the connection is closing.
		return "Closing Connection on Port: " + port; 
	}

	// Enter the Main program
	public static void main(String[] args){
		 // define variable used to hold the String hostname and integer port.
		 int PORT;
		 String HOST="localhost";
		
		 // Create a Scanner to allow the user to define the client port
		 System.out.print("Enter the port number for the server: ");
		 Scanner input = new Scanner(System.in);
		 PORT = input.nextInt();
		 // If the port entered is greater than 9999 or less than 0 return an error. Otherwise continue
		 if(PORT >9999|| PORT <0){
		 	// print the suggested port range
			 System.out.println("Port must be in the range from 0 to 9999 ");
		 } else {
			// Call Method to start Server at selected port
			 System.out.println(StartClient(HOST,PORT));
		 }

	}
}
