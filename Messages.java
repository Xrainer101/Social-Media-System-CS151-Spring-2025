import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Messages {
    private Consumer user = null;
    private List<Message> SentMessages = new ArrayList<>();
    private List<Message> ReceivedMessages = new ArrayList<>();
    private static Scanner scanner;

    public Messages(Consumer user) {
        this.user = user;
    }

    class Message {
        String message;
        String name;
        Message(String message, String name) {
            this.message = message;
            this.name = name;
        }
    }
    public void sendMessage() {
        System.out.print("Enter recipient's name: ");
        String recipient = scanner.nextLine();

        if (!user.getFriendsUsernames().contains(recipient)) {
            System.out.println(recipient + " is not in your friends list. Add them first.");
            return;
        }

        System.out.print("Enter your message: ");
        String message = scanner.nextLine();
        SentMessages.add(new Message(message, recipient));
        Consumer friend = user.checkFriend(recipient);
        Messages friendMessages = friend.getMessages();
        friendMessages.ReceivedMessages.add(new Message(message, user.getUsername()));
        System.out.println("Message sent to " + recipient);
    }

    
    public void receiveMessage() {}
    public void displayMessages() {}
    public void displayFriends() {}

    public void UI (Scanner s) {
        scanner = s;
        while (true) {
            System.out.println("\n1. Send a message");
            System.out.println("2. Receive a message");
            System.out.println("3. Display messages");
            System.out.println("4. Display friends");
            System.out.println("5. Go Home");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                   user.getMessages().sendMessage();
                    break;
                case 2:
                    user.getMessages().receiveMessage();
                    break;
                case 3:
                    user.getMessages().displayMessages();
                    break;
                case 4:
                    user.getMessages().displayFriends();
                    break;
                case 5:
                    System.out.println("Exiting the messaging system...");
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
