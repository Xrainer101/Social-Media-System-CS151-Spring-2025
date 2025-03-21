import java.util.ArrayList;
import java.util.Scanner;
public class Consumer extends User {
   private String username;
   private String password;
   private ArrayList<Group> groupInvites;
   private ArrayList<ChildUser> children;

   public Consumer(String username, String password) {
      this.username = username;
      this.password = password;
      groupInvites = new ArrayList<>();
   }

   public void makeChildAccount(String username, String password) {
         Scanner s = new Scanner(System.in);   
         System.out.println("Enter username: ");
         String childUsername = s.nextLine();
         System.out.println("Enter password: ");
         String childPassword = s.nextLine();
         ChildUser child = new ChildUser(childUsername, childPassword);
         Users.add(child);
         //Maybe implement usersGet method in forum
         children.add(child);
         System.out.println();
         System.out.println("Child successful created");
         System.out.println();
         System.out.println();
   }

   public void manageChildren(ChildUser child) {
      Scanner s = new Scanner(System.in);   
      System.out.println(children.toString());
      //Change if it does not actually print out the children in a list
      System.out.println("Enter index (a number) of child you want to manage, starting with 0");
      int childIndex = s.nextInt();
      ChildUser currChild = children.get(childIndex);
      currChild.changeSettings();
      System.out.println("Child successfuly managed");
   }

   public void acceptGroupInvite(Group group) {
      boolean foundGroup = false;
      for (Group g : groupInvites) {
         if (group.getGroupName().equals(g.getGroupName())) {
            foundGroup = true;
         }
      }
      if (foundGroup) {
         groupInvites.remove(group);
      } else if (!foundGroup) {
         System.out.println(username + " is not in the group " + group.getGroupName());
      }
   }

   @Override
   public String getUsername() {
      return username;
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public ArrayList<Group> getGroupInvites() {
      return groupInvites;
   }

   @Override
   public void addGroupInvite(Group group) {
      boolean duplicateName = false;
      for (Group g : groupInvites) {
         if (group.getGroupName().equals(g.getGroupName())) {
            duplicateName = true;
         }
      }
      if (duplicateName) {
         System.out.println(username + " already has a pending invite to " + group.getGroupName());
      } else if (!duplicateName) {
         groupInvites.add(group);
      }
   }

    @Override
    public void changeSettings() {
        //Perhaps there should be a change username or change password method
        Scanner s = new Scanner(System.in);
        boolean done = false;
        
        while(!done) {
         System.out.println("UN: Change username");
         System.out.println("PW: Change password");
         System.out.println("EXIT: End management");
         String input = s.nextLine();
         if(input.equals("UN")) {
             System.out.println("Enter username: ");
             username = s.nextLine();
             System.out.println("Username successfully changed");
             System.out.println();
         }
         else if(input.equals("PW")) {
             System.out.println("Enter password: ");
             password = s.nextLine();
             System.out.println("Password successfully changed");
             System.out.println();
             
         }
         else if(input.equals("EXIT")) {
             done = true;
         } else {
            System.out.println("Invalid input");
         }
    }
   }

    @Override
    public void clear() {
      //delete all messages and posts and such  
      throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void metrics() {
         //show number of messages and posts and such
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete() {
      //delete all references to user (from all of the lists that it is in)
      throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void message(User) {
         //This was probably already implementing in one of your branches
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void requestJoin(Group) {
         //Probably already implemented somewhere else
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void usersOnline() {
         //Display all online users (though maybe this is a better forum method, in which case swap this out for a friending method)
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
