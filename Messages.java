import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Messages extends Sharer {
    private Consumer user = null;
    private List<Message> SentMessages = new ArrayList<>();
    private List<Message> ReceivedMessages = new ArrayList<>();
    private static Scanner scanner;

    public Messages(Consumer user) {
        this.user = user;
    }

    class Message {
        String message;
        Consumer person;
        Message(String message, Consumer person) {
            this.message = message;
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
        SentMessages.add(new Message(message, friend));
        Messages friendMessages = friend.getMessages();
        friendMessages.ReceivedMessages.add(new Message(message, user));
        System.out.println("Message sent to " + recipient);
    }
    
    public Message findReceivedMessage(Consumer from, String message) {
           for(Message M : ReceivedMessages) {
               if(M.message.equals(message) && M.person == from) {
                    return M;
               }
           }
           return null;
    }
    @Override
    public void deleteSharer() {
        boolean done = false;
        while(!done) {
            for(int i = 0; i < SentMessages.size(); i++) {
                System.out.println(i + ": " + SentMessages.get(i).message);
            }
            try {
                if(SentMessages.size() > 0) {
                    System.out.println("Enter index of the message you want to delete: ");
                    int index = Integer.parseInt(scanner.nextLine());
                    Message messageTobeDeleted = SentMessages.get(index);
                    Consumer receiver = messageTobeDeleted.person;
                    Messages receiverObject = receiver.getMessages();
                    Message receiverMessage = receiverObject.findReceivedMessage(user, messageTobeDeleted.message);
                    receiverObject.ReceivedMessages.remove(receiverMessage);
                    this.SentMessages.remove(messageTobeDeleted);
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
                System.out.println("Not valid index, try again");
                System.out.println();
            }
        }


    }
    @Override
    public void mostPopular() {
        HashMap<Consumer, Integer> messageCounts = new HashMap<Consumer, Integer>();
        for(Message m: ReceivedMessages) {
            if(!messageCounts.containsKey(m.person)) {
                messageCounts.put(m.person, 0);
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
            System.out.println(mostMessagesSent.getUsername() + "who sent " + max + "messages ");

        }
        
    }
    @Override
    public void edit () {
        boolean done = false;
        while(!done) {
            for(int i = 0; i < SentMessages.size(); i++) {
                System.out.println(i + ": " + SentMessages.get(i).message);
            }
            try {
                if(SentMessages.size() > 0) {
                    System.out.println("Enter index of the message you want to edit: ");
                    int index = Integer.parseInt(scanner.nextLine());
                    Message currentMessage = SentMessages.get(index);
                    System.out.println("Old message: " + currentMessage.message);
                    System.out.println("Enter edited message: ");
                    String editedMessage = scanner.nextLine();
                    currentMessage.message = editedMessage;
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

  

    public void displayMessages() {}
    public void receiveMessage() {}
    
    public void displayFriends() {}

    public void UI (Scanner s) {
        scanner = s;
        while (true) {
            System.out.println("\n1. Send a message");
            System.out.println("2. Receive a message");
            System.out.println("3. Display messages");
            System.out.println("4. Display friends");
            System.out.println("5. Go Home");
            System.out.println("6. Delete a sent message");
            System.out.println("7. Find the most popular sender");
            System.out.println("8. edit a sent message");
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
                case 6:
                   user.getMessages().deleteSharer();
                   break;
                case 7:
                   user.getMessages().mostPopular();
                   break;
                case 8:
                   user.getMessages().edit();
                   break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
