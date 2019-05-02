import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
*Author:    Group 4
*Date:      12/16/2014
*Purpose:   This acts as the gui that contains the points earned, and saves values in the buttons
*
*Caveat:    io errors.
*/
public class ScoreCard extends JPanel implements ActionListener{
   private JPanel jpScorec=null;
   private JButton jb6s=null;
   private JButton jb5s=null;
   private JButton jb4s=null;
   private JButton jb3s=null;
   private JButton jb2s=null;
   private JButton jb1s=null;
   private JButton jb3k=null;
   private JButton jb4k=null;
   private JButton jbFh=null;
   private JButton jbSs=null;
   private JButton jbLs=null;
   private JButton jbChance=null;
   private JTextField jtfYaht=null;
   //this constructor creates a jpanel to be inseted into the rest of the gui
   public ScoreCard(){
      jpScorec=new JPanel();
      jpScorec.setLayout(new GridLayout(0,2));
         jpScorec.add(new JLabel("6s:",JLabel.RIGHT));
         jb6s=new JButton();
         jpScorec.add(jb6s);
         jb6s.addActionListener(this);
         jpScorec.add(new JLabel("5s:",JLabel.RIGHT));
         jb5s=new JButton();
         jpScorec.add(jb5s);
         jb5s.addActionListener(this);
         jpScorec.add(new JLabel("4s:",JLabel.RIGHT));
         jb4s=new JButton();
         jpScorec.add(jb4s);
         jb4s.addActionListener(this);
         jpScorec.add(new JLabel("3s:",JLabel.RIGHT));
         jb3s=new JButton();
         jpScorec.add(jb3s);
         jb3s.addActionListener(this);
         jpScorec.add(new JLabel("2s:",JLabel.RIGHT));
         jb2s=new JButton();
         jpScorec.add(jb2s);
         jb2s.addActionListener(this);
         jpScorec.add(new JLabel("1s:",JLabel.RIGHT));
         jb1s=new JButton();
         jpScorec.add(jb1s);
         jb1s.addActionListener(this);
         jpScorec.add(new JLabel("3 of a kind:",JLabel.RIGHT));
         jb3k=new JButton();
         jpScorec.add(jb3k);
         jb3k.addActionListener(this);
         jpScorec.add(new JLabel("4 of a kind:",JLabel.RIGHT));
         jb4k=new JButton();
         jpScorec.add(jb4k);
         jb4k.addActionListener(this);
         jpScorec.add(new JLabel("Full house:",JLabel.RIGHT));
         jbFh=new JButton();
         jpScorec.add(jbFh);
         jbFh.addActionListener(this);
         jpScorec.add(new JLabel("Small straight:",JLabel.RIGHT));
         jbSs=new JButton();
         jpScorec.add(jbSs);
         jbSs.addActionListener(this);
         jpScorec.add(new JLabel("Large straight:",JLabel.RIGHT));
         jbLs=new JButton();
         jpScorec.add(jbLs);
         jbLs.addActionListener(this);
         jpScorec.add(new JLabel("Chance:",JLabel.RIGHT));
         jbChance=new JButton();
         jpScorec.add(jbChance);
         jbChance.addActionListener(this);
         jpScorec.add(new JLabel("Yahtzees:",JLabel.RIGHT));
         jtfYaht=new JTextField("0");
         jtfYaht.setEditable(false);
         jpScorec.add(jtfYaht);
         jtfYaht.addActionListener(this);
         add(jpScorec);
         //setVisible(true);
         //jpScorec.setVisible(true);
         //setSize(500, 500);
   }
   //this updates the values in the gui to the new values for categories that have not been already scored      
   public void update(int[] diceArray){      
         if(jb6s.isEnabled()){
            jb6s.setText(Integer.toString(diceArray[0]));
         }
         if(jb5s.isEnabled()){
            jb5s.setText(Integer.toString(diceArray[1]));
         }
         if(jb4s.isEnabled()){
            jb4s.setText(Integer.toString(diceArray[2]));
         }
         if(jb3s.isEnabled()){
            jb3s.setText(Integer.toString(diceArray[3]));
         }
         if(jb2s.isEnabled()){
            jb2s.setText(Integer.toString(diceArray[4]));
         }
         if(jb1s.isEnabled()){
            jb1s.setText(Integer.toString(diceArray[5]));
         }
         if(jb3k.isEnabled()){
            jb3k.setText(Integer.toString(diceArray[6]));
         }
         if(jb4k.isEnabled()){
            jb4k.setText(Integer.toString(diceArray[7]));
         }
         if(jbFh.isEnabled()){
            jbFh.setText(Integer.toString(diceArray[8]));
         }
         if(jbSs.isEnabled()){
            jbSs.setText(Integer.toString(diceArray[9]));
         }
         if(jbLs.isEnabled()){
            jbLs.setText(Integer.toString(diceArray[10]));
         }
         //
         jtfYaht.setText(""+(Integer.parseInt(jtfYaht.getText())+diceArray[11]));
         //
         if(jbChance.isEnabled()){
            jbChance.setText(Integer.toString(diceArray[12]));
         }
   }
   //reenable the buttons
   public void reenable(){
      jb6s.setEnabled(true);
      jb5s.setEnabled(true);
      jb4s.setEnabled(true);
      jb3s.setEnabled(true);
      jb2s.setEnabled(true);
      jb1s.setEnabled(true);
      jb3k.setEnabled(true);
      jb4k.setEnabled(true);
      jbFh.setEnabled(true);
      jbSs.setEnabled(true);
      jbLs.setEnabled(true);
      jtfYaht.setText("0");
      jbChance.setEnabled(true);
   }
   //this returns a int which is how many points that cave been confirmed by the player
   public int getScore(){
      int score=0;
      if(!jb6s.isEnabled()){
         score=score+Integer.parseInt(jb6s.getText());
      }
      if(!jb5s.isEnabled()){
         score=score+Integer.parseInt(jb5s.getText());
      }
      if(!jb4s.isEnabled()){
         score=score+Integer.parseInt(jb4s.getText());
      }
      if(!jb3s.isEnabled()){
         score=score+Integer.parseInt(jb3s.getText());
      }
      if(!jb2s.isEnabled()){
         score=score+Integer.parseInt(jb2s.getText());
      }
      if(!jb1s.isEnabled()){
         score=score+Integer.parseInt(jb1s.getText());
      }
      if(!jb3k.isEnabled()){
         score=score+Integer.parseInt(jb3k.getText());
      }
      if(!jb4k.isEnabled()){
         score=score+Integer.parseInt(jb4k.getText());
      }
      if(!jbFh.isEnabled()){
         score=score+Integer.parseInt(jbFh.getText());
      }
      if(!jbSs.isEnabled()){
         score=score+Integer.parseInt(jbSs.getText());
      }
      if(!jbLs.isEnabled()){
         score=score+Integer.parseInt(jbLs.getText());
      }
      if(!jbChance.isEnabled()){
         score=score+Integer.parseInt(jbChance.getText());
      }
      if(Integer.parseInt(jtfYaht.getText())>=1){
         score=score+50+(Integer.parseInt(jtfYaht.getText())-1)*100;
      }
      return score;
   }
   //this disables buttons as they are selected and with all of the if(enabled) statements, creates the memory of the scorecard
   public void actionPerformed(ActionEvent ae){
      JButton inQ=(JButton) ae.getSource();
      inQ.setEnabled(false);
   }
}