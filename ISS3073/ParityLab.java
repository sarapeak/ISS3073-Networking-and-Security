import java.util.*;
import java.text.*;
import java.io.*;

public class ParityLab
{
   public static void main(String[] args)
   {
      Scanner scan = new Scanner(System.in);
      
      //Ask the user for the columns and rows
      System.out.println("Enter in the amount of columns and rows:");
      int columns = scan.nextInt();
      int rows = scan.nextInt();
      
      //Create the 2D array
      int [][] array = new int [rows+1][columns+1];
      
      //Ask the user for the data in the array
      System.out.println("Enter in your data:");
      //Loops thorugh the 2D array
      for (int i = 0; i < rows; i++)
      {
         for (int j = 0; j < columns; j++)
         {
            int num = scan.nextInt();   //Takes in the number
            array[i][j] = num;          //Puts the num into the array
         }
      }
      
      //Enters the row parity data into the array
      System.out.println("Enter in the row parity data:");
      for (int i = 0; i < rows; i++)
      {
         int num = scan.nextInt();   //Takes in the number
         array[i][columns] = num;    //Puts the num into the array
      }
      
      //Enters in the column parity data into the array
      System.out.println("Enter in the column parity data:");
      for (int i = 0; i < columns; i++)
      {
         int num = scan.nextInt();   //Takes in the number
         array[rows][i] = num;    //Puts the num into the array
      }
      
      //Enters in the bottom right most parity
      System.out.println("Enter in the bottom right most bit:");
      int brBit = scan.nextInt();    //Gets the bit
      array[rows][columns] = brBit;  //Puts it into the array
      
      boolean error = false;   //Used to see if there were any errors
      
      int rCounter = 0;   //To count the number of 1's
      //A nested for loop to loop throuhg the entirety of the 2D array
      for (int i = 0; i < rows+1; i++)
      {
         for (int j = 0; j < columns+1; j++)
         {
            //If a position in the array has a 1, adds 1 to counter
            if (array[i][j] == 1)
            {
               rCounter++;
            }
            //If at the end of each row, if the counter is even, there is no error
            if (j == columns && rCounter%2 == 0)
            {
               rCounter = 0;
            }
            //If the counter is odd this code is executed
            else if (j == columns && (rCounter%2 != 0 || rCounter == 1))
            {
               error = true;
               //If we are at the parity row, we let the user know
               if (i == rows)
               {
                  System.out.println("Parity bit error in column parity bits!");
                  rCounter = 0;
               }
               //Otherwise we tell the user where the error was found
               else
               {
                  System.out.println("Error on row "+i);
                  rCounter = 0;
               }
            }
         } 
      }
      
      int cCounter = 0;   //To count the number of 1's
      //Loop through the 2D array
      for (int i = 0; i < columns+1; i++)
      {
         for (int j = 0; j < rows+1; j++)
         {
            //Adds one to counter if a 1 is found
            if (array[j][i] == 1)
            {
               cCounter++;
            }
            //If there was an even number of 1's then counter is reset
            if (j == rows && cCounter%2 == 0)
            {
               cCounter = 0;
            }
            //If there were an odd number of 1's, this code is executed
            else if (j == rows && (cCounter%2 != 0 || cCounter == 1))
            {
               error = true;
               //If we are at the parity column, we let the user know
               if (i == columns)
               {
                  System.out.println("Parity bit error in row parity bits!");
                  cCounter = 0;
               }
               //Otherwise we tell the user where the error was found
               else
               {
                  System.out.println("Error on column "+i);
                  cCounter = 0;
               }
            }
         }
      }
      
      //When no errors were found, this tells the user
      if (error == false)
      {
         System.out.println("No error was found in the data.");
      }
   }
}