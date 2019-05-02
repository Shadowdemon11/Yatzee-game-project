import java.net.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/**
*Author:    Group 4
*Date:      12/16/2014
*Purpose:   this is the gui and it does all the calculations 
*
*Caveat:    calcuation errors and gui crashes.
*/

public class GameHolder extends JFrame
{
   ScoreCard card;
   JTextField jtfScore;  
   public GameHolder(String ip)
   {
     
      final ArrayList<DiceButton> diceList = new ArrayList<DiceButton>();
      
      
      MyChatClient2 client = new MyChatClient2(ip);
      add(client, BorderLayout.EAST);
      JPanel gameBoard = new JPanel();
      JPanel diceBoard = new JPanel();
      JPanel scoreP=new JPanel();
      scoreP.setLayout(new FlowLayout(FlowLayout.RIGHT, 50, 15));
      scoreP.add(new JLabel("SCORE!"));
      jtfScore = new JTextField(10);
      scoreP.add(jtfScore);
      diceBoard.setLayout(new FlowLayout(FlowLayout.RIGHT, 50, 15));
      gameBoard.setLayout(new BorderLayout());
      gameBoard.setSize(500, 500);
      DiceButton dice;
      //this loop goes through the array and adds the dicebuttons to the diceBoard
      for(int i = 0; i < 5; i++)
      {
         dice = new DiceButton(i);
         dice.addActionListener(dice);
         diceList.add(dice);
         diceBoard.add(diceList.get(i));
      }
      //this rolls all the dice at once
      JButton rollAll = new JButton("Roll All Dice");
      rollAll.addActionListener(new ActionListener()
         {
         public void actionPerformed(ActionEvent ae)
         {
            for(int i = 0; i < 5; i++)
            {
                diceList.get(i).rollDice(); 
                
            }
         }
      });
      //this jbutton sends the arraylist to SendData and starts that thread
      JButton send = new JButton("Ready");
      send.addActionListener(new ActionListener()
         {
         public void actionPerformed(ActionEvent ae)
         {
            SendData send = new SendData(diceList);
            send.start();
            
         }
      });
      //this Jbutton resets the amount of rolls you get with each dice.
      JButton reset = new JButton("Reset Rolls");
      reset.addActionListener(new ActionListener()
         {
         public void actionPerformed(ActionEvent ae)
         {
            for(int i = 0; i < 5; i++)
            {
                diceList.get(i).resetRoll(); 
                card.reenable();
                
            }
            
         }
      });

      
      
      card = new ScoreCard();
      card.setVisible(true);
      gameBoard.add(diceBoard, BorderLayout.CENTER);
      gameBoard.add(rollAll, BorderLayout.SOUTH);
      gameBoard.add(send, BorderLayout.WEST);
      gameBoard.add(reset,BorderLayout.EAST);
      
      add(gameBoard, BorderLayout.CENTER);
      
      add(card, BorderLayout.WEST);
      add(scoreP, BorderLayout.NORTH);
      setSize(1000, 1000);
      setLocationRelativeTo(null);
      setVisible(true);
      setTitle("Yahtzee Game");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
   }
   //this class DiceButton extends jbutton and implements actionlistener holds the side of the dice that is randomly made and shows the icon of the side of the dice.
   class DiceButton extends JButton implements ActionListener
   {
      private int diceSide=1;
      final Icon dice1 = new ImageIcon("dice1.jpg");
      final Icon dice2 = new ImageIcon("dice2.jpg");
      final Icon dice3 = new ImageIcon("dice3.jpg");
      final Icon dice4 = new ImageIcon("dice4.jpg");
      final Icon dice5 = new ImageIcon("dice5.jpg");
      final Icon dice6 = new ImageIcon("dice6.jpg");
      
      private int name;
      private int numOfRolls = 0;
      
      public DiceButton(int na)
      {
         name = na;
         addIcon();
      }
      //call rollDice
      public void actionPerformed(ActionEvent ae)
         {
            rollDice();  
         }
      //set the side of the dice
      public void setSide(int side)
      {
         diceSide = side;
         //System.out.println(name+" "+diceSide);
      }
      //add the icon to the button
      public void addIcon()
      {
         if(diceSide == 1)
         {
            setIcon(dice1);
         }
         else if(diceSide == 2)
         {
            setIcon(dice2);
         }
         else if(diceSide == 3)
         {
            setIcon(dice3);
         }
         else if(diceSide == 4)
         {
            setIcon(dice4);
         }
         else if(diceSide == 5)
         {
            setIcon(dice5);
         }
         else if(diceSide == 6)
         {
            setIcon(dice6);
         }
         
      }
      //rolls the dice and makes sure that you cant roll more than 3 times.
      public void rollDice()
      {
         Random rand = new Random();
         int  n = rand.nextInt(6) + 1;
         // checks if you roll the dice 3 times
         if(numOfRolls == 3)
         {
            System.out.println("you used up all of your rolls for this dice");
         }
         else
         {
            setSide(n);
            addIcon();
            numOfRolls++;
         }
      }
      //get the number of times you rolled
      public int getNumOfRolls()
      {
         return numOfRolls;
      }
      
      public int getRoll()
      {
         return diceSide;
      }
      
      public void resetRoll()
      {
         numOfRolls = 0;
      }
   }
   //sendData gets a arraylist than send the dice number to  DiceAnalyzer and update the scorecard and adds the score to the jtextfield
   class SendData extends Thread
   {
      ArrayList<DiceButton> diceList;
      int[] dicenum = new int[5];
      public SendData(ArrayList<DiceButton> arr)
      {
         diceList = arr;
      }
      //runs the thread
      public void run()
      {
         //loops throgh the array and adds the number from the dice to it
         for(int i = 0; i < 5; i++)
         {
            dicenum[i] = diceList.get(i).getRoll();
         }
         DiceAnalyzer analyz = new DiceAnalyzer(dicenum);
         
         card.update(analyz.getResults());
         
         jtfScore.setText(""+card.getScore());
         analyz.reset();
          
      }
   }
   
   
   
   
   public static void main(String [] args)
   {
     
     new GameHolder(args[0]);
     
   }
}