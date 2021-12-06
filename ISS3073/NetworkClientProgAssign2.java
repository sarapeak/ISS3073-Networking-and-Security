import java.net.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class NetworkClientProgAssign2
{
        public static void main(String[] args) throws IOException
        {
                //Port number that matches the Server
                int PortNumber = 9959;

                //Creates a Scanner
                Scanner scan = new Scanner(System.in);

                //Creates a socket and connects it to the server
                Socket MyClient = new Socket("127.0.0.1", PortNumber);

                //Used to take input and output information from the Server
                //Initializes the output and input
                DataOutputStream output = new DataOutputStream(MyClient.getOutputStream());
                DataInputStream input = new DataInputStream(MyClient.getInputStream());

                System.out.println("Client connected!");
                int logout = 0;
                boolean login = false;
                String option;

                //Continues the program until the user specifies to turn off the server
                do
                {
                        //Shows the user the menu options
                        System.out.println("What would you like to do?");
                        System.out.println("1. Login\n2. Logout\n3. Send Message\n4. Turn off server.");
                        System.out.print(">> ");
                        //Takes in the user's input
                        option = scan.next();
                        output.writeUTF(option);   //Outputs the option to the server

                        //A switch statement with the different menu options
                        switch (option)
                        {
                                //Menu option 1: Login
                                case "1":
                                        login = input.readBoolean();   //Reads in the login boolean
                                        //If already logged in
                                        if (login == true)
                                        {
                                                System.out.println("Already logged in.");
                                        }
                                        //If not logged in
                                        else
                                        {
                                                System.out.println("Enter the username and password.");
                                                System.out.print(">> ");
                                                //Scans in the user and pass
                                                String user = scan.next();
                                                String pass = scan.next();
                                                //Outputs the user and pass
                                                output.writeUTF(user);
                                                output.writeUTF(pass);
                                                //Prints out if login was successful or not
                                                String valid = input.readUTF();
                                                System.out.println(valid);
                                                //If successful, will print out the messages
                                                if (valid.equals("Login successful!"))
                                                {
                                                        String messages = input.readUTF();
                                                        System.out.println(messages);
                                                }
                                        }
                                        break;
                                //Menu option 2: Logout
                                case "2":
                                        login = input.readBoolean();
                                        //Takes in the info from the server if person is logged in
                                        if (login == true)
                                        {
                                                login = input.readBoolean();
                                                String logoff1 = input.readUTF();
                                                System.out.println(logoff1);
                                        }
                                        //Takes in the info from the server if person is not logged in
                                        else
                                        {
                                                String logoff2 = input.readUTF();
                                                System.out.println(logoff2);
                                        }
                                        break;
                                //Menu option 3: Send a message
                                case "3":
                                        login = input.readBoolean();
                                        //If logged in
                                        if (login == true)
                                        {
                                                System.out.println("Who do you want to message?");   //Prompts the user
                                                System.out.print(">> ");
                                                String toUser = scan.next();   //Takes in the username
                                                scan.nextLine();   //To allow nextLine() to work prperly
                                                System.out.print("What is your message? (single line)\n>> ");
                                                String message = scan.nextLine();   //Takes in the message
                                                //Outputs to the server
                                                output.writeUTF(toUser);
                                                output.writeUTF(message+"\n");
                                                //Takes in the server response
                                                String yesNoUser = input.readUTF();
                                                System.out.println(yesNoUser);   //Prints out if the user is in database
                                        }
                                        //If not logged in
                                        else
                                        {
                                                //Bad message
                                                String messageFail = input.readUTF();
                                                System.out.println(messageFail);
                                        }
                                        break;
                                //Menu option 4: Shut down the server
                                case "4":
                                        logout = input.readInt();   //Takes in the server shutdown int
                                        System.out.println("Server shutting down...");
                                        System.out.println("Exiting!");
                                        break;
                                //If user puts in bad input
                                default:
                                        System.out.println("Please enter in a valid menu option.");
                                        break;
                        }
                }while (logout != 1);
        }
}