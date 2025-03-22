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

    public void modifyUser(ArrayList<Moderator> moderators, Consumer consumer) {
        boolean isModerator = false;
        System.out.println("User: \n   " + consumer.getUsername());
        System.out.println("Groups:");
        for (Group g: consumer.getGroupMembership()) {
            System.out.println("   " + g.getGroupName());
        }
        for (Moderator m: moderators) {
            if (m.getModerator().getUsername().equalsIgnoreCase(consumer.getUsername())) {
                isModerator = true;
            }
        }
        System.out.print(consumer.getUsername() + " is a ");
        if (isModerator) {
            System.out.println("global moderator");
            System.out.println("Cannot modify another global moderator");
        } else {
            System.out.println("regular user");
            System.out.println("d: delete user");
            System.out.println("m: make global moderator");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equalsIgnoreCase(("d"))) {
                deleteUser(consumer);
            } else if (input.equalsIgnoreCase("m")) {
                moderators.add(new Moderator(consumer));
                System.out.println("User " + consumer.getUsername() + " is now a global moderator.");
            } else {
                System.out.println("Returning to main menu.");
            }
        }
    }

    public void deleteUser(Consumer user) {
        System.out.println("Do you really want to delete: " + user.getUsername() + "?");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input \"Y\" or \"N\"");
        String input = sc.nextLine();
        if (input.equalsIgnoreCase("Y")) {
            Forum.Users.remove(user);
            System.out.println(user.getUsername() + " is deleted.");
        } else if (input.equalsIgnoreCase("N")) {
            System.out.println(user.getUsername() + " is not deleted.");
        } else {
            System.out.println("Invalid input, returning back to main menu.");
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

    public void deletePost(ArrayList<Post> posts, int index) {
            posts.remove(index);
            System.out.println("Post #" + index + " has been removed.");
    }
}
