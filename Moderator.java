import java.util.Scanner;
import java.util.ArrayList;
public class Moderator {
    private Consumer moderator;

    public Moderator(Consumer moderator) {
        this.moderator = moderator;
    }

    public Consumer getModerator() {
        return moderator;
    }

    public void deleteUser(Consumer user) {
        System.out.println("Do you really want to delete: " + user.getUsername() + "?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(!input.equals("Y")|| !input.equals("N")) {
            System.out.println("Please input \"Y\" or \"N\"");
            input = sc.nextLine();
        }
        if (input.equals("Y")) {
            Forum.Users.remove(user);
            System.out.println(user.getUsername() + " is deleted.");
        } else if (input.equals("N")) {
            System.out.println(user.getUsername() + " is not deleted.");
        }
    }

    public void deleteGroup(ArrayList<Group> groups, Group group) {
        System.out.println("Do you really want to delete: " + group.getGroupName() + "?");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input \"Y\" or \"N\"");
        String input = sc.nextLine();
        if (input.equalsIgnoreCase("Y")) {
            groups.remove(group);
            System.out.println(group.getGroupName() + " is deleted.");
        } else if (input.equalsIgnoreCase("N")) {
            System.out.println(group.getGroupName() + " is not deleted.");
        } else {
            System.out.println("Invalid input, returning back to main menu.");
        }
    }

    public void deletePost() {
        System.out.println();
    }

}
