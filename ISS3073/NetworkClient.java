import java.net.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class NetworkClient
{
   public static void main(String[] args) throws IOException
   {
      //Port number that matches the Server
      int PortNumber = 9959;
      int result = 0;   //Result set to 0
      //Creates a Scanner
      Scanner scan = new Scanner(System.in);

      //Used to take input and output information from the Server
      DataInputStream input;
      DataOutputStream output;

      //Creates a socket and connects it to the server
      Socket MyClient = new Socket("127.0.0.1", PortNumber);
      System.out.println("Client connected!");

      String op = "";   //Initializes the string
      boolean checker = false;   //Used to check if the operator is valid
      System.out.println("Please enter in an operator:");

      //Loops through until a valid operator is chosen
      while(!checker)
      {
         op = scan.next();
         //A switch statement for the different operators
         switch(op)
         {
            case "+":
               checker = true;
               break;
            case "-":
               checker = true;
               break;
            case "/":
               checker = true;
               break;
            case "*":
               checker = true;
               break;
            //The default is for invalid input
            default:
               System.out.println("Invalid operator. Please choose +, -, /, or *.");
         }
      }
      //Makes the string operator into a char
      char operator = op.charAt(0);

      //Initializes the output and input
      output = new DataOutputStream(MyClient.getOutputStream());
      input = new DataInputStream(MyClient.getInputStream());

      output.writeChar(operator);   //Sends the operator to the Server

      //Takes in the numbers from the Server
      int num1 = input.readInt();
      int num2 = input.readInt();

      //Checks to see which operator was used
      if(operator == '+')
      {
         result = num1 + num2;   //Adding
      }
      else if(operator == '-')
      {
         result = num1 - num2;   //Subtracts
      }
      else if(operator == '/')
      {
         result = num1 / num2;   //Divides
      }
      else if(operator == '*')
      {
         result = num1 * num2;   //Multiplies
      }

      System.out.println("Result is "+result);   //Prints out the result
   }
}