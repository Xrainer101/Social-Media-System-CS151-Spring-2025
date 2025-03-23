import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Messages extends Sharer {
    private Consumer user = null;
    private List<Message> sentMessages = new ArrayList<>();
    private List<Message> receivedMessages = new ArrayList<>();
    private static Scanner scanner;

    public Messages(Consumer user) {
        this.user = user;
    }

    class Message {
        String text;
        Consumer person;
        Message(String text, Consumer person) {
            this.text = text;
            this.person = person;
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
        
        Consumer friend = user.checkFriend(recipient);
        sentMessages.add(new Message(message, friend));
        
        friend.getMessages().receivedMessages.add(new Message(message, user));
        System.out.println("Message sent to " + recipient);
    }
    
    public Message findReceivedMessage(Consumer from, String message) {
           for(Message M : receivedMessages) {
               if(M.text.equals(message) && M.person == from) {
                    return M;
               }
           }
           return null;
    }
    @Override
    public void deleteSharer() {
        boolean done = false;
        while(!done) {
            for(int i = 0; i < sentMessages.size(); i++) {
                System.out.println(i + ": " + sentMessages.get(i).text);
            }
            try {
                if(sentMessages.size() > 0) {
                    System.out.println("Enter index of the message you want to delete: ");
                    int index = Integer.parseInt(scanner.nextLine());
                    Message messageTobeDeleted = sentMessages.get(index);
                    Consumer receiver = messageTobeDeleted.person;
                    Messages receiverObject = receiver.getMessages();
                    Message receiverMessage = receiverObject.findReceivedMessage(user, messageTobeDeleted.text);
                    receiverObject.receivedMessages.remove(receiverMessage);
                    this.sentMessages.remove(messageTobeDeleted);
                    System.out.println("Message deleted successfully sent from " + user.getUsername() + " and received by " + receiver.getUsername());
                    System.out.println();
                    done = true;
                       
                }
                else {
                    System.out.println("No messages to delete!");
                    done = true;
                }
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Not valid index, try again");
                System.out.println();
            }

        }


    }
    @Override
    public void mostPopular() {
        HashMap<Consumer, Integer> messageCounts = new HashMap<Consumer, Integer>();
        for(Message m: receivedMessages) {
            if(!messageCounts.containsKey(m.person)) {
                messageCounts.put(m.person, 1);
            }
            else {
                messageCounts.put(m.person, messageCounts.get(m.person) + 1);
            }
        }

        if(messageCounts.isEmpty()) {
            System.out.println("You have not received any messages");
        }
        else {
            int max = 0;
            Consumer mostMessagesSent = null;
            for(Consumer p: messageCounts.keySet() ) {
                if(mostMessagesSent == null) {
                    mostMessagesSent = p;
                    max = messageCounts.get(p);
                }
                else if(messageCounts.get(p) > max) {
                    mostMessagesSent = p;
                    max =  messageCounts.get(p);
                }
            }
            System.out.println("The person that sent the most messages is: ");
            System.out.println(mostMessagesSent.getUsername() + " who sent " + max + " messages ");

        }
        
    }
    @Override
    public void edit () {
        boolean done = false;
        while(!done) {
            for(int i = 0; i < sentMessages.size(); i++) {
                System.out.println(i + ": " + sentMessages.get(i).text);
            }
            try {
                if(sentMessages.size() > 0) {
                    System.out.println("Enter index of the message you want to edit: ");
                    int index = Integer.parseInt(scanner.nextLine());
                    Message currentMessage = sentMessages.get(index);
                    Consumer receiver = currentMessage.person;
                    Message receiverMessageObject = receiver.getMessages().findReceivedMessage(user, currentMessage.text);
                    System.out.println("Old message: " + currentMessage.text);
                    System.out.println("Enter edited message: ");
                    String editedMessage = scanner.nextLine();
                    currentMessage.text = editedMessage;
                    receiverMessageObject.text = editedMessage;
                    System.out.println(" Message edited successfully");
                    System.out.println();
                    done = true;

                }
                else {
                    System.out.println("No messages to edit");
                    System.out.println();
                    done = true;
                }
                

            }
            catch(Exception e) {
                System.out.println("Not valid index, try again");
                System.out.println();
            }

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
        System.out.println(msg.person.getUsername() + ": " + msg.text);
    }
    }
    public void receiveMessage() {
    if (receivedMessages.isEmpty()) {
        System.out.println("No new messages.");
        return;
    }
    
    System.out.println("\nNew Messages:");
    for (int i = 0; i < receivedMessages.size(); i++) {
        Message msg = receivedMessages.get(i);
        System.out.println(msg.person.getUsername() + ": " + msg.text);
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
    public void UI (Scanner s) {
        scanner = s;
        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. Send a message");
            System.out.println("2. Receive messages");
            System.out.println("3. Display sent messages");
            System.out.println("4. Display friends");
            System.out.println("5. Go back to main menu");
            System.out.println("6. Delete a sent message");
            System.out.println("7. Find the most popular sender");
            System.out.println("8. edit a sent message");
            System.out.print("Enter your choice: ");

            String ch = scanner.nextLine();
            

            switch (ch) {
                case "1": sendMessage(); break;
                case "2": receiveMessage(); break;
                case "3": displaySentMessages(); break;
                case "4": displayFriends(); break;
                case "5": exit = true; System.out.println("Returning to main menu..."); break;
                case "6": deleteSharer(); break;
                case "7": mostPopular(); break;
                case "8": edit(); break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
