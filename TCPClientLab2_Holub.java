import java.net.*;
import java.io.*;
import java.util.Scanner.*;

public class TCPClientLab2_Holub {
	public static void main (String args[]) {
	// arguments supply message and hostname of destination
	    Socket s = null;
	    try{
	    	int serverPort = 7896;
         // change args[1] to args[0] so we can get the host (because we arent including a message in the command line)
	   	s = new Socket(args[0], serverPort);  
         
      //using a buffered reader for USER input (the InputStreamReader)
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      //a Printwriter to write the input to the server 
      PrintWriter writer = new PrintWriter(s.getOutputStream(), true);
      //another buffered reader but to recieve the information from the server (the total)
      BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
 
      
      // new code for arithmatic
      //while loop to keep receiving user input until the user wants to stop 
      while(true){
         System.out.print("Enter a number 5 digits or less (or END to stop):");
         String data = in.readLine();
         if(data.equals("END")){
            break;
         }
         //checking to ensure that the input is not longer than 5 characters
         if(!data.matches("\\d{1,5}")){
            System.out.println("The data must be a number with 1 to 5 digits.");
            continue;
         }
         //printing the input data to the server so it can perfrom the add opertaion
         writer.println(data);
         System.out.println(data + " has been sent to the server!");
      
      }//end while
      
      //reading the information sent from the server to display the total sum of numbers to the client 
      writer.println("EOF");
      String sumOfNums = reader.readLine();
      System.out.println("The total sum of the numbers sent to the server is: " + sumOfNums);
           
       	    }catch (UnknownHostException e){
		System.out.println("Sock:"+e.getMessage()); 
	    }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
    	    }catch (IOException e){System.out.println("IO:"+e.getMessage());}
	   finally {if(s!=null) try {s.close();}catch (IOException e) {System.out.println ("close:"+ e.getMessage());}}
  	}
}
