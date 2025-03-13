import java.util.Scanner;
public class Moderator {
    private Consumer mod;
    private Group g;
    public Moderator(Consumer mod) {
        this.mod = mod;
    }

    public void deleteUser(Consumer user) {
        System.out.println("Do you really want to delete: " + user.getUsername() + "?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().substring(0,1).toUpperCase();
        while(!input.equals("Y")|| !input.equals("N")) {
            System.out.println("Please input \"Y\" or \"N\"");
            input = sc.nextLine().substring(0,1).toUpperCase();
        }
        if (input.equals("Y")) {
            Forum.Users.remove(user);
            System.out.println(user.getUsername() + " is deleted.");
        } else if (input.equals("N")) {
            System.out.println(user.getUsername() + " is not deleted.");
        }
    }

}
