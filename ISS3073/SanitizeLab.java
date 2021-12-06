import java.util.*;
import java.text.*;
import java.io.*;

public class SanitizeLab
{
   public static void main(String[] args)
   {
      Scanner scan = new Scanner(System.in);
      
      System.out.println("Username:");
      String username = scan.next();
      System.out.println("Password:");
      String password = scan.next();
      
      String lowerUser = username.toLowerCase();
      String lowerPass = password.toLowerCase();
      
      String newUser = "";
      String newPass = "";
      
      for (int i = 0; i < username.length(); i++)
      {
         if (!Character.toString(username.charAt(i)).equals(Character.toString(lowerUser.charAt(i))) || Character.toString(username.charAt(i)).equals(";"))
         {
            newUser += username.charAt(i);
         }
      }
      
      for (int i = 0; i < password.length(); i++)
      {
         if (!Character.toString(password.charAt(i)).equals(Character.toString(lowerPass.charAt(i))) || Character.toString(password.charAt(i)).equals(";"))
         {
            newPass += password.charAt(i);
         }
      }
      
      System.out.println("\nUsername: "+newUser);
      System.out.println("Password: "+newPass);
   }
}