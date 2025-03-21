import java.util.ArrayList;
public class Consumer extends User {
   private String username;
   private String password;
   private ArrayList<Group> groupInvites;

   public Consumer(String username, String password) {
      this.username = username;
      this.password = password;
      groupInvites = new ArrayList<Group>();
   }

   public String getUsername() {
      return username;
   }

   public String getPassword() {
      return password;
   }

   public ArrayList<Group> getGroupInvites() {
      return groupInvites;
   }

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
}
