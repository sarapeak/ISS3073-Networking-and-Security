import java.net.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class NetworkServerProgAssign2
{
        public static void main(String[] args) throws IOException
        {
                //Port number that matches the Client
                int PortNumber = 9959;

                //Waits for information or input to be sent to the server
                ServerSocket listener = new ServerSocket(PortNumber);
                Socket socket = listener.accept();   //Accepts the info sent

                //Used to take input and output information from the client
                //Initializes the output and input
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                DataInputStream input = new DataInputStream(socket.getInputStream());

                System.out.println("Connected server!");

                //A scanner to read the text file
                Scanner userPassFile = new Scanner(new File("shadow.txt"));
                int counter = 0;
                String line = "";
                //Loops through the file
                while (userPassFile.hasNext())
                {
                        line += userPassFile.next()+" ";   //Adds onto the line String
                        counter++;   //Adds 1 to counter
                }
                String[] words = line.split(" ");   //Splits the String by spaces
                int row = counter/2;
                //Create a 2D array to store the usernames and passwords
                String[][] array = new String[row][2];
                counter = 0;

                //Put the usernames and passwords into the 2D array
                for (int i = 0; i < row; i++)
                {
                        for (int j = 0; j < 2; j++)
                        {
                                array[i][j] = words[counter];
                                counter++;
                        }
                }

                int logout = 0;   //Sets the logout to 0
                boolean login = false;   //Sets the login deafult to false
                //Loops through until shutting down the server is called
                do
                {
                        String option = input.readUTF();   //Takes in the option from the client
                        //A switch statement of the different menu options
                        switch (option)
                        {
                                //Logins in the user, tells the user they are already logged in, or invalid user or pass
                                case "1":
                                        output.writeBoolean(login);   //Tells the client if the person is logged in or not
                                        if (login == false)
                                        {
                                                //Takes in the user and pass
                                                String user = input.readUTF();
                                                String pass = input.readUTF();
                                                counter = 0;
                                                //Loops through the array
                                                for (int i = 0; i < row; i++)
                                                {
                                                        //If the array finds a username match
                                                        if (array[i][0].equals(user))
                                                        {
                                                                //If the username and passwor match
                                                                if (array[i][1].equals(pass))
                                                                {
                                                                        //Successfully logs in
                                                                        String success = "Login successful!";
                                                                        login = true;   //Makes login bool true
                                                                        output.writeUTF(success);

                                                                        String fileName = user+".txt";   //Creates the file name
                                                                        File file = new File(fileName);
                                                                        //If the file exists
                                                                        if (file.exists() == true)
                                                                        {
                                                                                Scanner scan = new Scanner(file);
                                                                                String message = "";
                                                                                int count = 0;
                                                                                //Loops through the file
                                                                                while (scan.hasNextLine())
                                                                                {
                                                                                        count++;   //Adds 1 to count
                                                                                        //Adds onto message
                                                                                        message += "Message "+count+": "+scan.nextLine()+"\n";
                                                                                }
                                                                                //Sends the completed message to the client
                                                                                output.writeUTF("You have "+count+" messages:\n"+message);
                                                                                file.delete();   //Deletes the file
                                                                        }
                                                                        //When there are no messages
                                                                        else
                                                                        {
                                                                                String noMessage = "You have 0 messages:";
                                                                                output.writeUTF(noMessage);
                                                                        }
                                                                }
                                                                //If the password is not a match
                                                                else
                                                                {
                                                                        //Tells the user a bad password was given
                                                                        String passFail = "Invalid password.";
                                                                        output.writeUTF(passFail);
                                                                }
                                                                break;   //Breaks the loop
                                                        }
                                                        //If there is no matching username
                                                        else if (counter == row-1)
                                                        {
                                                                //Fails the try
                                                                String userFail = "Invalid username.";
                                                                output.writeUTF(userFail);
                                                        }
                                                        counter++;   //Adds 1 to the counter
                                                }
                                        }
                                        break;
                                //Logs the user off of tells them they are not logged in
                                case "2":
                                        output.writeBoolean(login);
                                        //If logged in
                                        if (login == true)
                                        {
                                                login = false;   //Makes login to false
                                                String logoff1 = "Successfully logged off!";
                                                output.writeBoolean(login);
                                                output.writeUTF(logoff1);
                                        }
                                        //Else tells the user they are not logged in
                                        else
                                        {
                                                String logoff2 = "Not logged in!";
                                                output.writeUTF(logoff2);
                                        }
                                        break;
                                //Creates messages for other valid users
                                case "3":
                                        counter = 0;
                                        output.writeBoolean(login);
                                        //Allows user to send a message
                                        if (login == true)
                                        {
                                                //Takes in the client input
                                                String toUser = input.readUTF();
                                                String message = input.readUTF();

                                                //Loops through the array rows
                                                for (int i = 0; i < row; i++)
                                                {
                                                        //If it finds user match
                                                        if (array[i][0].equals(toUser))
                                                        {
                                                                //Creates a file name
                                                                String fileName = toUser+".txt";
                                                                File file = new File(fileName);
                                                                //If the file does not exist, the file is created
                                                                if (file.exists() == false)
                                                                {
                                                                        file.createNewFile();
                                                                }
                                                                //Makes is possible to write and edit the file
                                                                FileWriter fileWritter = new FileWriter(file.getName(),true);
                                                                BufferedWriter bw = new BufferedWriter(fileWritter);
                                                                bw.write(message);   //Appends the messages
                                                                bw.close();   //Closes the file

                                                                //Tells the user the message was saved
                                                                String yesUser = "Message saved for "+toUser;
                                                                output.writeUTF(yesUser);
                                                                break;
                                                        }
                                                        //If there is no user match, tells the user
                                                        else if (counter == row-1)
                                                        {
                                                                String noUser = "Person does not exist in the database!";
                                                                output.writeUTF(noUser);
                                                        }
                                                        counter++;   //Adds 1 to counter
                                                }
                                        }
                                        //Tells user they are not logged in
                                        else
                                        {
                                                String messageFail = "Not logged in!";
                                                output.writeUTF(messageFail);
                                        }
                                        break;
                                case "4":
                                        logout = 1;   //Changes logout to 1; ends the loop
                                        output.writeInt(logout);   //Outputs it to the client
                                        break;
                        }
                }while (logout != 1);
        }
}