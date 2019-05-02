import java.io.*;
import java.net.*;
import java.util.*;
/**
*Author:    Group 4
*Date:      11/6/2014
*Purpose:   This Program was to get messages from the clients and send them to all to see. 
*
*Caveat:    io errors.
*/
public class ChatServer {
   Vector<PrintWriter> pwv = new Vector<>();
   
   Socket cs = null;
   ServerSocket ss = null;   
   boolean created=false;
   
   //this is the main class, it starts up everything else
   public static void main(String[] args) {
      new ChatServer();
   }//main
   
   //this is the contructor for the server,it sets up the sockets and starts the thread to handle individidual connections
   public ChatServer() {
      try {
         ss = new ServerSocket(16789);
         System.out.println("server is running...");
         while(true){ 	
            System.out.println("Waiting for client");
            cs = ss.accept(); 				
            ChatServerThreadReceive cstr = new ChatServerThreadReceive(cs);
            cstr.start();
         } // end while
      }//try
      catch( BindException be ) {
         System.out.println("server already running ");
      }
      catch( IOException ioe ) {
         System.out.println("IO Error");
      }
   }//constructor
   
   //this is the constructor for the threads
   class ChatServerThreadReceive extends Thread {
      Socket s;
      Date d = new Date();
      public ChatServerThreadReceive(Socket s) {
         this.s = s;
      }//constructor
      
      //this is the code that is has multiple instances made of it to handle the various connections
      //each connection has a single thread associated with it that takes in messages from it's connection
      //then goes through the vector array of print writers and tells each one to send the message to it's connection
      //the print writers are added to the vector on thread creation
      public void run() {      
         try{
         BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
         PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
         int pwvIndex=pwv.size()+1;
         pwv.add(pw);
         String message=null;         
         do{
              message = br.readLine();
              message=s.getInetAddress()+" at " + d +" sent: "+message;
              if(message==null){
              break;
              }
              System.out.println("Server read " + message);
              for(int i=0;i<pwv.size();i++){
               pwv.get(i).println(message);
               pw.flush();
              }
         }while (message!=null);
         //someone has left
         br.close();
         pw.close();
         pwv.removeElement(pwvIndex);
         //remove connection from vector array
         }catch(IOException ioe) {
            System.out.println(s.getInetAddress() + " disconnected");
         }//catch
      }//run
   }//ClassThreadServerReceive
}//class