import java.util.ArrayList;
import java.util.Scanner;
public class Forum {
    ArrayList<Consumer> Consumer = new ArrayList<Consumer>();
   public static void main(String[] args) {
      displayHomeInterface();
      

   }
   public static void displayHomeInterface() {
     System.out.println("1.Login");
     System.out.println("2. Sign Up");
     System.out.println("3: EXIT (Lose all data)");
   }
}