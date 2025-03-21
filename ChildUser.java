import java.util.ArrayList;
import java.util.Scanner;
public class ChildUser extends User {
    private String username;
    private String password;
    private ArrayList<Group> groupInvites;

    public ChildUser(String username, String password) {
      this.username = username;
      this.password = password;
      groupInvites = new ArrayList<>();
    }

    private void manageChildInvitations() {
      boolean done = false;
      while(!done) {
        Scanner s = new Scanner(System.in);
        System.out.println(groupInvites.toString());
        System.out.println("INSPECT: Look at a specific invite");
        System.out.println("ACCEPT ALL: Accept all invites");
        System.out.println("REJECT ALL: Reject all invites");
        System.out.println("EXIT: End invitation management");
        String ans = s.nextLine();
        if(ans.equals("INSPECT")) {
          System.out.println("Enter number of invitation you want to inspect (starting at 0)");
          int groupIndex = s.nextInt();
          Group groupInvite = groupInvites.get(groupIndex);
          System.out.println(groupInvite.toString());
          System.out.println("Type ACCEPT to accept the invitation");
          System.out.println("Type REJECT to reject the invitation");
          ans = s.nextLine();
          if(ans.equals("ACCEPT")) {
            //Implement the same as however you guys accept a groupInvite in consumer

          } else if(ans.equals("REJECT")) {
            //I guess remove that invite from groupInvites
          }
        } else if(ans.equals("ACCEPT ALL")) {
          //Go through all of them and accept them
        } else if(ans.equals("REJECT ALL")) {
          //clear groupInvites
        } else if(ans.equals("EXIT")) {
          done = true;
        } else {
          System.out.println("Invalid input");
        }
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
         System.out.println("GROUP: Approve Group Invites");
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
         else if(input.equals("GROUP")) {
            manageChildInvitations();
         }
         else if(input.equals("EXIT")) {
             done = true;
         } else {
            System.out.println("Invalid input");
         }
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
