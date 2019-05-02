import java.net.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/**
*Author:    Group 4
*Date:      11/6/2014
*Purpose:   This Program was to send a message to the server and read messages from other clients 
*
*Caveat:    io errors.
*/
public class MyChatClient2 extends JPanel
{
   //all in 
   JTextArea sendText;
   JTextField sendTF;
   JScrollPane sp;
   Button sendB;
   
   Socket s;
   OutputStream out;
   InputStream in;
   boolean loop = true;
   String ip;
   
   /**
   Constructor sets up the client with a textarea, textfield and a sent button than sends and checks for messages.
   */
   public MyChatClient2(String ip2)
   {
      ip = ip2;
      
      setLayout(new BorderLayout());
      JPanel sendStart = new JPanel();
      sendStart.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
      sendText = new JTextArea();
      sendB = new Button("send");
      sendTF = new JTextField(20);
      JLabel text = new JLabel("Type msg here: ");
      sendStart.add(text);
      sendStart.add(sendTF);
      add(sendStart, BorderLayout.NORTH);
      
      //a action that calls a method send
      sendB.addActionListener(new ActionListener() {
   		public void actionPerformed(ActionEvent ae)
   	   	{
               send();
               
      		}
      	});
      add(sendB, BorderLayout.SOUTH);
      sp = new JScrollPane(sendText);
      add(sp, BorderLayout.CENTER);
      setSize(500, 500);
      
      setVisible(true);
      
      try
      {
         s = new Socket( ip, 16789);
         sendText.append("Connected to the Server");
         in = s.getInputStream();
         BufferedReader bin = new BufferedReader(new InputStreamReader(in));
         
         //while loop that if loop is true than keep trying to get messages if not catch a io exception that breaks out of the loop
         
         
            PassiveRead read = new PassiveRead();
            read.start();
         
      }
      catch(UnknownHostException uhe) {
			System.out.println("no host");
			
         sendText.append("no host cannot write to");
      }
      catch(IOException ioe)
		{
			System.out.println("IO error connect");
         sendText.append("no host cannot read please try again later");
			
		}
   }
   
   //method that sends the messages to the server
   public void send()
   {
      try
      {
   	   out = s.getOutputStream();
         PrintWriter pout = new PrintWriter(out);
         pout.println(sendTF.getText());
         pout.flush();
         
         
      }
      catch(IOException ioe)
		{
			System.out.println("Cant send msg");
			
		}
      catch(NullPointerException npe)
      {
         
         sendText.append("\n no host cannot write to \n");
      }
   }
   
   
   class PassiveRead extends Thread
   {
      BufferedReader bin = new BufferedReader(new InputStreamReader(in));
      public void run()
      {
          while(loop == true)
         {
            try
            {
               
              sendText.append("\n" + bin.readLine() + "\n");
               
              
            }
            catch(IOException ioe)
   		   {
   			   System.out.println("Cant get msg");
               loop = false;
   			   
   		   }
         }
      }
   }
	
}