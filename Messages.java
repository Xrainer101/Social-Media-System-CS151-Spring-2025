import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Messages {
    private List<String> messages = new ArrayList<>();
    private List<String> friends = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void sendMessage() {
        System.out.print("Enter recipient's name: ");
        String recipient = scanner.nextLine();

        if (!friends.contains(recipient)) {
            System.out.println(recipient + " is not in your friends list. Add them first.");
            return;
        }

        System.out.print("Enter your message: ");
        String message = scanner.nextLine();
        messages.add("Sent to " + recipient + ": " + message);
        System.out.println("Message sent to " + recipient);
    }

    public void addFriend() {
        System.out.print("Enter friend's name: ");
        String friendName = scanner.nextLine();

        if (friends.contains(friendName)) {
            System.out.println(friendName + " is already a friend.");
        } else {
            friends.add(friendName);
            System.out.println(friendName + " added to friends list.");
        }
    }
    
    public void receiveMessage() {}
    public void displayMessages() {}
    public void displayFriends() {}

    public static void main(String[] args) {
        Messages messaging = new Messages();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Send a message");
            System.out.println("2. Receive a message");
            System.out.println("3. Display messages");
            System.out.println("4. Display friends");
            System.out.println("5. Add a friend");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    messaging.sendMessage();
                    break;
                case 2:
                    messaging.receiveMessage();
                    break;
                case 3:
                    messaging.displayMessages();
                    break;
                case 4:
                    messaging.displayFriends();
                    break;
                case 5:
                    messaging.addFriend();
                    break;
                case 6:
                    System.out.println("Exiting the messaging system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

