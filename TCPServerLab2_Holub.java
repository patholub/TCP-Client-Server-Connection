import java.net.*;
import java.io.*;
public class TCPServerLab2_Holub {
    public static void main (String args[]) {
	try{
   //basic connection setup on the server side
		int serverPort = 7896; 
		ServerSocket listenSocket = new ServerSocket(serverPort);
      System.out.println("Server is listening on poort number: " + serverPort);
      Socket clientSocket = listenSocket.accept();
			Connection c = new Connection(clientSocket);
         System.out.println("Server connected to the client...");
		
      //using buffered reader to get user input from client and PrintWriter to print back to the client  
      int totalSum = 0;
			BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            //variable to store data and send
            String data;
            //while loop to keep taking user input from the client until the user is done 
            while ((data = reader.readLine()) != null) {
                if (data.equals("EOF")) {
                    break;
                }
               //convert String to int and adding the number just recived to the total
                try {
                    int num = Integer.parseInt(data);
                    totalSum += num;
                } catch (NumberFormatException e) {
                    System.out.println("Received invalid data: " + data);
                }
               
            }
            //once user is done sending numbers, send the total back to the client 
            writer.println(totalSum);
            System.out.println("Total sum: " + totalSum);
		
	} catch(IOException e) {System.out.println("Listen :"+e.getMessage());}
    }
}
//connection class included in the base program 
class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
	    try {
		clientSocket = aClientSocket;
		in = new DataInputStream( clientSocket.getInputStream());
		out =new DataOutputStream( clientSocket.getOutputStream());
		this.start();
	     } catch(IOException e)  {System.out.println("Connection:"+e.getMessage());}
	}
}
