import java.util.*;
import java.text.*;
import java.io.*;

public class ConnectFour
{  
   //Checks to see if the game is tied
   public static int checkTie(int win, String board[][])
   {
      int tieCount = 0;
      //Loops through the game board
      for(int i = 0; i < 6; i++)
      {
         for(int j = 0; j < 7; j++)
         {
            if(!board[i][j].equals("."))
            {
               tieCount++;   //Adds 1 to count
            }
         }
      }
      //If all of the positions are filled and there is not winner, it ends up as a tie
      if(tieCount == 42)
      {
         System.out.println("\nIt's a tie! You both have won and lost!");
         win = 1;   //Ends the program
      }
      return win;
   }
   
   public static void main(String[] args)
   {
      //Prints out the title of the game
      System.out.println("C  O  N  N  E  C  T");
      System.out.print("    F  O  U  R");
      
      //Creates a 2D array which will store the connect four board
      String[][] connectBoard = new String[7][7];
      //Loop through from the rows to fill in the 2D array
      for(int row = 0; row < 7; row++)
      {
         System.out.println();
         //Loops through from the columns to fill in the 2D array
         for(int col = 0; col < 7; col++)
         {
            //If it is the last row, it adds a barrier
            if(row == 6)
            {
               connectBoard[row][col] = "-";
            }
            //If it is not the last row, it adds a playable area
            else
            {
               connectBoard[row][col] = ".";
            }
            //Prints out the board
            System.out.print(connectBoard[row][col]+"  ");
         }
      }
      
      //Variables that will be used through the program
      int win = 0;
      String playerNum = "1";
      int colNum = 0;
      int counter = 1;
      Scanner scan = new Scanner(System.in);   //Scans in the user's input
      
      //Loops through the game until a player gets a connect four
      while(win == 0)
      {  
         //Tells the specified player that it is their turn
         System.out.println("\nPlayer "+playerNum+": Enter the column you wish to play in (1 - 7).");
         System.out.print(">> ");
         colNum = scan.nextInt();   //Scans the player's input
         colNum--;   //Takes one away from colNum to match the number of the array
         
         //Checks to see if the user inputted a valid input
         while(colNum > 6 || colNum < 0)
         {
            System.out.println("That is an invalid column. Please choose again.");
            System.out.print(">> ");
            colNum = scan.nextInt();
            colNum--;
         }
         
         //Loops through the rows on the board
         for(int i = 5; i >= 0; i--)
         {
            //Checks what is in the specified array position
            if(connectBoard[i][colNum].equals("."))
            {
               //Checks to see if it is player 1
               if(playerNum.equals("1"))
               {
                  //Adds a 1 to the board to indicate that player 1 played
                  connectBoard[i][colNum] = "1";
                  break;   //Ends the loop
               }
               //Checks to see if it is player 2
               else if(playerNum.equals("2"))
               {
                  //Adds a 2 to the board to indicate that player 2 played
                  connectBoard[i][colNum] = "2";
                  break;   //Ends the loop
               }
            }
            //If the column indicated is full, this section of code executes
            else if(connectBoard[0][colNum].equals("1") || connectBoard[0][colNum].equals("2"))
            {
               int badCol = colNum;   //Makes a variable equal to the colNum
               //Loops through until a valid column number is inputted
               while(badCol == colNum)
               {
                  System.out.println("That column is full. Please choose again.");
                  System.out.print(">> ");
                  colNum = scan.nextInt();
                  colNum--;
               }
               i = 6;   //Need to make i 6 because it will subtract one when the rotation finishes
            }
         }
         
         //Prints out the board
         for(int i = 0; i < 7; i++)
         {
            System.out.println();
            for(int j = 0; j < 7; j++)
            {
               System.out.print(connectBoard[i][j]+"  ");   //Spaces the board positions out
            }
         }
         
         //Checks to see if someone won
         int tracker = 0;
         //Horizontal Check
         if(win != 1)
         {
            //Loops through the board
            for(int row = 5; row >= 0; row--)
            {
               for(int col = 0; col < 7; col++)
               {
                  //Checks to see if the player is inhabiting a specific spot
                  if(connectBoard[row][col].equals(playerNum))
                  {
                     tracker++;   //Adds one to the tracker
                     if(tracker == 4)   //If the tracker is 4, it breaks out of the loop
                     {
                        break;
                     }
                  }
                  //If the position is occupied by a different player and is not empty
                  else if(!connectBoard[row][col].equals(playerNum) && !connectBoard[row][col].equals("."))
                  {
                     tracker = 0;   //Tracker is reset
                  }
                  //If there is nothing beside the player's number, the tracker is reset
                  else if(connectBoard[row][col].equals("."))
                  {
                     tracker = 0;
                  }
               }
               //When the tracker is equal to 4, the game ends
               if(tracker == 4)
               {
                  win = 1;   //The variable win is changed to 1 causing the while loop to break
                  System.out.println("\nCongratulations on winning Player "+playerNum+"!");   //Tells the player they won
                  break;   //Breaks the for loop
               }
               //If the tracker fails to reach 4, it is reset to check the next row
               else
               {
                  tracker = 0;
               }
            }
         }
         
         //Vertical Check
         //If someone has not won another way, this section of code executes
         if(win != 1)
         {
            tracker = 0;   //Sets tracker to 0
            //Loops through the board
            for(int col = 0; col < 7; col++)
            {
               for(int row = 5; row >= 0; row--)
               {
                  //Checks to see if the player is inhabiting a specific spot
                  if(connectBoard[row][col].equals(playerNum))
                  {
                     tracker++;   //Adds one to the tracker
                     if(tracker == 4)   //If the tracker is 4, it breaks out of the loop
                     {
                        break;
                     }
                  }
                  //If the position is occupied by a different player and is not empty
                  else if(!connectBoard[row][col].equals(playerNum) && !connectBoard[row][col].equals("."))
                  {
                     tracker = 0;   //Tracker is reset
                  }
               }
               //When the tracker is equal to 4, the game ends
               if(tracker == 4)
               {
                  win = 1;   //The variable win is changed to 1 causing the while loop to break
                  System.out.println("\nCongratulations on winning Player "+playerNum+"!");   //Tells the player they won
                  break;   //Breaks the for loop
               }
               //If the tracker fails to reach 4, it is reset to check the next row
               else
               {
                  tracker = 0;
               }
            }
         }
         
         //Diagonal Check - Up to the Right
         //If someone has not won another way, this section of code executes
         if(win != 1)
         {
            //Loops through the board
            for(int r = 5; r >= 0; r--)
            {
               for(int c = 0; c < 4; c++)
               {
                  //Checks if the row is unplayable
                  if(r-1 < 0 || r-2 < 0 || r-3 < 0)
                  {
                     //does nothing
                  }
                  //Checks to see if there is a possible diagonal
                  else if(connectBoard[r][c].equals(playerNum) && connectBoard[r-1][c+1].equals(playerNum) && connectBoard[r-2][c+2].equals(playerNum) && connectBoard[r-3][c+3].equals(playerNum))
                  {
                     win = 1;   //The variable win is changed to 1 causing the while loop to break
                     System.out.println("\nCongratulations on winning Player "+playerNum+"!");   //Tells the player they won
                     break;   //Breaks the for loop
                  }
               }
            }
         }
         
         //Diagonal Check - Up to the Left
         //If someone has not won another way, this section of code executes
         if(win != 1)
         {
            //Loops through the board
            for(int r = 5; r >= 0; r--)
            {
               for(int c = 6; c >= 3; c--)
               {
                  //Checks if the row is unplayable
                  if(r-1 < 0 || r-2 < 0 || r-3 < 0)
                  {
                     //does nothing
                  }
                  //Checks to see if there is a possible diagonal
                  else if(connectBoard[r][c].equals(playerNum) && connectBoard[r-1][c-1].equals(playerNum) && connectBoard[r-2][c-2].equals(playerNum) && connectBoard[r-3][c-3].equals(playerNum))
                  {
                     win = 1;   //The variable win is changed to 1 causing the while loop to break
                     System.out.println("\nCongratulations on winning Player "+playerNum+"!");   //Tells the player they won
                     break;   //Breaks the for loop
                  }
               }
            }
         }

         counter++;   //Counts each play
         //Uses the counter variable to indicate if it is going to be player 1 or 2
         if(counter % 2 == 0)   //If the result is 0, player 2 goes next
         {
            playerNum = "2";
         }
         else   //If the result is not 0, player 1 goes next
         {
            playerNum = "1";
         }
         
         win = checkTie(win, connectBoard);
      }
   }
}