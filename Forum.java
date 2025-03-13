import java.util.ArrayList;
import java.util.Scanner;
public class Forum {
    public static ArrayList<Consumer> Users = new ArrayList<Consumer>();
    private static Consumer current = null;
    static boolean home = true;
   public static void main(String[] args) {
       Scanner s = new Scanner(System.in);
       Consumer current = null;
       while(true) {
         if(home) {
           setup(s);
         }
         if(!home) {
            functionality(s);
         }

       }

    }
   public static void setup(Scanner s) {
     boolean doneSetup = false; 
     while(!doneSetup) {
        System.out.println("L: Login");
        System.out.println("S: Sign Up");
        System.out.println("EXIT: exits program(loses all data)");
        System.out.println("Enter input based on key");
        String input = s.nextLine();
        if(input.equals("S")) {
            System.out.println("Enter username: ");
            String username = s.nextLine();
            System.out.println("Enter password: ");
            String password = s.nextLine();
            Users.add(new Consumer(username, password));
            System.out.println();
            System.out.println("Signup successful");
            System.out.println();
            System.out.println();
        }
        else if(input.equals("L")) {
            System.out.println("Enter username: ");
            String username = s.nextLine();
            System.out.println("Enter password: ");
            String password = s.nextLine();
            if(validateInfo(username, password)) {
               System.out.println();
               System.out.println("Login successful!");
               System.out.println();
               System.out.println();
               doneSetup = true;
               home = false;
            }
            else {
                System.out.println();
                System.out.println("Incorrect password or username");
                System.out.println("Try again!");
                System.out.println();
                System.out.println();
            }
            
        }
        else if(input.equals("EXIT")) {
            System.exit(0);
        }
        
     }
     
   }
   // please add a printline statement in the same format and an if statement.
   // to implement functionality. 
   public static void functionality(Scanner s) {
       boolean done = false;
      while(!done) {
         System.out.println("mp: Make post");
         System.out.println("vp: viewPosts");
         System.out.println("vf: viewFriend");
         System.out.println("af: add friend");
         System.out.println("lo: logout");
         System.out.println("EXIT: exits program(loses all data)");
         System.out.println("Enter input(2 character): ");
         String input = s.nextLine();
         if(input.equals("lo")) {
            logout();
            done = true;
         }
         else if(input.equals("EXIT")) {
            System.exit(0);
         }
          
       }
   }
   public static boolean validateInfo(String username, String password) {
          for(Consumer s: Users) {
             if(s.getUsername().equals(username) && s.getPassword().equals(password) ) {
                 current = s;
                 return true;
             }
          }
          return false;
   }
   public static void logout() {
      home = true; 
      current = null;
   }

}