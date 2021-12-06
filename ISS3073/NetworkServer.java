import java.net.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class NetworkServer
{
   public static void main(String[] args) throws IOException
   {
      //Port number that matches the Client
      int PortNumber = 9959;
      int result = 0;   //Result is set to 0
      //Creates a scanner to read in input form the user
      Scanner scan = new Scanner(System.in);

      //Used to take input and output information from the client
      DataInputStream input;
      DataOutputStream output;

      //Waits for information or input to be sent to the server
      ServerSocket listener = new ServerSocket(PortNumber);
      Socket socket = listener.accept();   //Accepts the info sent
      System.out.println("Connected server!");
      System.out.println("Please enter two numbers:");

      //Makes sure the input is correct
      String useless;
      //Loops until the scan is an integer
      while(!scan.hasNextInt())
      {
         System.out.println("Please input an integer.");
         useless = scan.next();
      }
      int num1 = scan.nextInt();
      //Loops until the scan is an integer
      while(!scan.hasNextInt())
      {
         System.out.println("Please input an integer for the second number.");
         useless = scan.next();
      }
      int num2 = scan.nextInt();

      //Initializes the input and output variables
      output = new DataOutputStream(socket.getOutputStream());
      input = new DataInputStream(socket.getInputStream());

      char operator = input.readChar();   //Reads in the input char

      //Outputs the nums to the client
      output.writeInt(num1);
      output.writeInt(num2);

      //Checks to see what kind of operator is being sent to the server
      //The nums are then calculated depending on the operator
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
      System.out.println("Result is "+ result);   //Prints out the result
   }
}