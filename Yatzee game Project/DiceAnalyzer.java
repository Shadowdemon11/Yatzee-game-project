import java.util.*;
/**
*Author:    Group 4
*Date:      12/16/2014
*Purpose:   This class analyzes the dice, takes in array of ints that are the dice values and gives returns an array of ints of points
*
*Caveat:    io errors.
*/
public class DiceAnalyzer{
   private int[]diceList;
   private int sixCount=0;
   private int fiveCount=0;
   private int fourCount=0;
   private int threeCount=0;
   private int twoCount=0;
   private int oneCount=0;
   private int b3Kind=0;
   private int b4Kind=0;
   private int fullHouseCounter=0;
   private int smallStraightCounter=0;
   private int largeStraightCounter=0;
   private int yahtzeeCounter=0;
   private int chance=0;
   //this constrictor is used for when analyze and reset are used
   public DiceAnalyzer(){
   
   }   
   //this constrictor takes in an int array and analyzes it and returns point values
   public DiceAnalyzer(int[] _diceIn){
      int[]diceList;      
      diceList=_diceIn;
      Arrays.sort(diceList);
      for(int i = 0; i < 5; i++)
      {
         System.out.println(_diceIn[i]);
      }
      //
      sixes(diceList);
      fives(diceList);
      fours(diceList);
      threes(diceList);
      twos(diceList);
      ones(diceList);
      a3OfKind(diceList);
      a4OfKind(diceList);
      fullHouse(diceList);
      smallStraight(diceList);
      largeStraight(diceList);
      yahtzee(diceList);
      chance(diceList);
   }
   //this gives how many sixes are present
   public void sixes(int[] _dice){
      for(int i=0;i<5;i++){
         if(_dice[i]==6){
            sixCount++;
         }
      }
   }
   //this gives how many fives are present
   public void fives(int[] _dice){
      for(int i=0;i<5;i++){
         if(_dice[i]==5){
            fiveCount++;
         }
      }
   }
   //this gives how many fours are present
   public void fours(int[] _dice){
      for(int i=0;i<5;i++){
         if(_dice[i]==4){
            fourCount++;
         }
      }
   }
   //this gives how many threes are present
   public void threes(int[] _dice){
      for(int i=0;i<5;i++){
         if(_dice[i]==3){
            threeCount++;
         }
      }
   }
   //this gives how many twos are present
   public void twos(int[] _dice){
      for(int i=0;i<5;i++){
         if(_dice[i]==2){
            twoCount++;
         }
      }
   }
   //this gives how many ones are present
   public void ones(int[] _dice){
      for(int i=0;i<5;i++){
         if(_dice[i]==1){
            oneCount++;
         }
      }
   }
   //this gives if a 3 of kind is present, and the score
   public void a3OfKind(int[] _dice){
         for(int j=0;j<3;j++){
            if(_dice[j]==_dice[j+1]&&_dice[j+1]==_dice[j+2]){
               b3Kind=_dice[0]+_dice[1]+_dice[2]+_dice[3]+_dice[4];
            }
         }
   }
   //this gives if a 4 of kind is present, and the score
   public void a4OfKind(int[] _dice){
         for(int j=0;j<2;j++){
            if(_dice[j]==_dice[j+1]&&_dice[j+1]==_dice[j+2]&&_dice[j+2]==_dice[j+3]){
               b4Kind=b3Kind=_dice[0]+_dice[1]+_dice[2]+_dice[3]+_dice[4];
            }
         }
   }
   //this gives if a full house is present
   public void fullHouse(int[] _dice){
      if(_dice[0]==_dice[1]&&_dice[2]==_dice[3]&&_dice[3]==_dice[4]&&_dice[0]!=_dice[3]){
         fullHouseCounter=1;
      }
      if(_dice[0]==_dice[1]&&_dice[1]==_dice[2]&&_dice[3]==_dice[4]&&_dice[0]!=_dice[3]){
         fullHouseCounter=1;
      }
   }
   //this gives if a small straight is present
   public void smallStraight(int[] _dice){
      int correct=0;
         if(_dice[0]==(_dice[1]-1)){
            correct++;
         }
         if(_dice[1]==(_dice[2]-1)){
            correct++;
         }
         if(_dice[2]==(_dice[3]-1)){
            correct++;
         }
         if(_dice[3]==(_dice[4]-1)){
            correct++;
         }
         if(correct>=3){
            smallStraightCounter=1;
         }
      }
   //this gives if a large straight is present
   public void largeStraight(int[] _dice){
         if(_dice[0]==(_dice[1]-1)&&(_dice[1]-1)==(_dice[2]-2)&&(_dice[2]-2)==(_dice[3]-3)&&(_dice[3]-3)==(_dice[4]-4)){
            largeStraightCounter=1;
         }
   }
   //this gives if a yahtzee is present
   public void yahtzee(int[] _dice){
      //for(int i=0;i<4;i++){
         if(_dice[0]==_dice[1]&&_dice[1]==_dice[2]&&_dice[2]==_dice[3]&&_dice[3]==_dice[4]){
            yahtzeeCounter++;
         }
      //}
   }
   //this gives how much the chance is
   public void chance(int[] _dice){
      chance=_dice[0]+_dice[1]+_dice[2]+_dice[3]+_dice[4];
   }
   //returns and int array with the point values of each category, yahtzee is special and that is done in the scorecard class
   public int[] getResults(){
      //array of point values always given in the same order
      int[]results=new int[13];
      results[0]=sixCount*6;
      results[1]=fiveCount*5;
      results[2]=fourCount*4;
      results[3]=threeCount*3;
      results[4]=twoCount*2;
      results[5]=oneCount;
      results[6]=b3Kind;
      results[7]=b4Kind;
      results[8]=fullHouseCounter*25;
      results[9]=smallStraightCounter*30;
      results[10]=largeStraightCounter*40;
      results[11]=yahtzeeCounter;
      results[12]=chance;
      return results;
   }
   ///////////////////////////////////////////////////////method to analyze
   public void analyze(int[] _diceIn){
      int[]diceList;      
      diceList=_diceIn;
      Arrays.sort(diceList);
      
      sixes(diceList);
      fives(diceList);
      fours(diceList);
      threes(diceList);
      twos(diceList);
      ones(diceList);
      a3OfKind(diceList);
      a4OfKind(diceList);
      fullHouse(diceList);
      smallStraight(diceList);
      largeStraight(diceList);
      yahtzee(diceList);
      chance(diceList);
   }
   //resets the counts
   public void reset(){
      sixCount=0;
      fiveCount=0;
      fourCount=0;
      threeCount=0;
      twoCount=0;
      oneCount=0;
      b3Kind=0;
      b4Kind=0;
      fullHouseCounter=0;
      smallStraightCounter=0;
      largeStraightCounter=0;
      chance=0;
   }
}