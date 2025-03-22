import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Messages {
    private Consumer user;
    private List<Message> sentMessages = new ArrayList<>();
    private List<Message> receivedMessages = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public Messages(Consumer user) {
        this.user = user;
        this.sc = new Scanner(System.in);
    }

    class Message {
        String text;
        String sender;

        Message(String text, String sender) {
            this.text = text;
            this.sender = sender;
        }
    }

    public void sendMessage() {
        System.out.print("Recipient's username: ");
        String recpt = sc.nextLine();

        if (!user.getFriendsUsernames().contains(recpt)) {
            System.out.println(recpt + " is not in your friends list. Add them first. Go back to main menu and add your friend");
            return;
        }

        System.out.print("Enter your message: ");
        String message = sc.nextLine();

        sentMessages.add(new Message(message, recpt));
        
        Consumer friend = user.checkFriend(recpt);
        friend.getMessages().receivedMessages.add(new Message(message, user.getUsername()));

        System.out.println("Message sent to " + recpt);
    }

    public void receiveMessage() {
    if (receivedMessages.isEmpty()) {
        System.out.println("No new messages.");
        return;
    }
    
    System.out.println("\nNew Messages:");
    for (int i = 0; i < receivedMessages.size(); i++) {
        Message msg = receivedMessages.get(i);
        System.out.println(msg.sender + ": " + msg.text);
    }
}

public void displaySentMessages() {
    if (sentMessages.isEmpty()) {
        System.out.println("You haven't sent any messages yet.");
        return;
    }
    
    System.out.println("\nSent Messages:");
    for (int i = 0; i < sentMessages.size(); i++) {
        Message msg = sentMessages.get(i);
        System.out.println(msg.sender + ": " + msg.text);
    }
}

public void displayFriends() {
    List<String> friends = user.getFriendsUsernames();
    if (friends.isEmpty()) {
        System.out.println("You haven’t added any friends yet.");
        return;
    }
    System.out.println("\nYour Friends:");
    for (String friend : friends) {
        System.out.println("- " + friend);
    }
}

        public void UI(Scanner s) {
        this.sc = s;
        boolean exit = false;

        while (!exit) {
            System.out.println("\n1. Send a message");
            System.out.println("2. Receive messages");
            System.out.println("3. Display sent messages");
            System.out.println("4. Display friends");
            System.out.println("5. Go back to main menu");
            System.out.print("Enter your choice: ");

            String ch = sc.nextLine();

            switch (ch) {
                case "1": sendMessage(); break;
                case "2": receiveMessage(); break;
                case "3": displaySentMessages(); break;
                case "4": displayFriends(); break;
                case "5": exit = true; System.out.println("Returning to main menu..."); break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
