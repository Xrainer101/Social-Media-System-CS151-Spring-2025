import java.util.ArrayList;
import java.util.Scanner;
public class Forum {
    static ArrayList<Consumer> Users = new ArrayList<Consumer>();
    private static ArrayList<Group> groups = new ArrayList<Group>();
    private static Consumer current = null;
    private static ArrayList<Moderator> globalModerators = new ArrayList<Moderator>();
    private static Moderator currentModerator = null;
    static boolean home = true;
   public static void main(String[] args) {
       Scanner s = new Scanner(System.in);
       //Dummy data -> login with one of these accounts already.
       Consumer user1 = new Consumer("Sza", "1234");
       Consumer user2 = new Consumer("Matt", "1234");
       globalModerators.add(new Moderator(user1));
       Users.add(user1);
       Users.add(user2);
       user1.addFriend(user2);
       user2.addFriend(user1);
       while(true) {
         if(home) {
           setup(s);
         }
         if(!home) {
            functionality(s);
         }

       }

    }
   public static void setup(Scanner s) {
     boolean doneSetup = false;
     while(!doneSetup) {
        System.out.println("L: Login");
        System.out.println("S: Sign Up");
        System.out.println("EXIT: exits program(loses all data)");
        System.out.println("Enter input based on key");
        String input = s.nextLine();
        tryExit(input, s);
        if (input.equals("S")) {
            System.out.println("Enter username: ");
            String username = s.nextLine();
            System.out.println("Enter password: ");
            String password = s.nextLine();
            boolean exist = false;
            for(Consumer U: Users) {
               if(U.getUsername().equals(username)) {
                  exist = true;
                  break;
               }
            }
            if(!exist) {
            Users.add(new Consumer(username, password));
            System.out.println();
            System.out.println("Signup successful");
            System.out.println();
            System.out.println();
            }
            else {
               System.out.println("This username is already in use!");
               System.out.println("try again!");
               System.out.println();
            }
        }
        else if(input.equals("L")) {
            System.out.println("Enter username: ");
            String username = s.nextLine();
            System.out.println("Enter password: ");
            String password = s.nextLine();
            if(validateInfo(username, password)) {
               System.out.println();
               System.out.println("Login successful!");
               System.out.println();
               System.out.println();
               doneSetup = true;
               home = false;
            }
            else {
                System.out.println();
                System.out.println("Incorrect password or username");
                System.out.println("Try again!");
                System.out.println();
                System.out.println();
            }

        }


     }

   }
   // please add a printline statement in the same format and an if statement.
   // to implement functionality.
   public static void functionality(Scanner s) {
       boolean done = false;
      while(!done) {
          System.out.println("mp: Make post");
          if (current.getModeratorStatus()) {
              System.out.println("mu: modify user");
          }
          System.out.println("vp: viewPosts");
          System.out.println("vf: viewFriend");
          System.out.println("af: add friend");
          System.out.println("vg: view groups");
          System.out.println("pm: private messaging");
          System.out.println("lo: logout");
          System.out.println("EXIT: exits program(loses all data)");
          System.out.println("Enter input(2 character): ");
          String input = s.nextLine();
          tryExit(input, s);
          if (input.equals("lo")) {
              logout();
              done = true;
          } else if (input.equals("mp")) {
              System.out.println("Enter Post: ");
              String post = s.nextLine();
              current.addPost(post);
              System.out.println();
              System.out.println("Post added successfully");
              System.out.println();
              System.out.println();
              } else if (input.equals("vp")) {
                  current.viewPosts(s, null);
              } else if(input.equals("af")) {
               System.out.println("Enter Friend username: ");
               String username = s.nextLine();
               Consumer result = null;
               for(Consumer U : Users) {
                  if (U.getUsername().equals(username) && U != current) {
                       result = U;
                  }
               }
               if(result != null && current.checkFriend(result.getUsername()) == null) {
                  if(current.checkRequests(result)) {
                     System.out.println("This friend sent a request!");
                     System.out.println("Accept request from " + result.getUsername() + "(y/n)");
                     String choice = s.nextLine();
                     if(choice.equals("y")) {
                        result.addFriend(current);
                        current.addFriend(result);
                        System.out.println("Added successfully");
                        System.out.println();
                     }
                     else {
                        System.out.println("Not added");
                        System.out.println();
                     }
   
                  }
                  else {
                     if(result.checkRequests(current)) {
                        System.out.println("Pending request");
                        System.out.println("Waiting on friend");
                     }
                     else {
                     System.out.println("Sending friend Request");
                     System.out.println();
                     result.addRequest(current);
                     }
   
                  }
               }
               else {
                  System.out.println("Not added because user does not exist, already added, or trying to add yourself");
                  System.out.println();
               }
              } else if (input.equals("vf")) {
                  System.out.println("Enter Friend username to view their posts: ");
                  String username = s.nextLine();
                  Consumer result = current.checkFriend(username);
                  if (result != null) {
                      result.viewPosts(s, current);
                  } else {
                      System.out.println("Friend does not exist or you are trying add yourself as a friend");
                  }

              } else if (input.equals("pm")) {
                  current.getMessages().UI(s);
              } else if (input.equalsIgnoreCase("mu") && (current.getModeratorStatus())) {
              System.out.println("Input the name of the user you would like to modify: ");
              Consumer selectedConsumer = null;
              input = s.nextLine();
              for (Consumer c : Users) {
                  if (c.getUsername().equalsIgnoreCase(input)) {
                      selectedConsumer = c;
                  }
              }
              if (selectedConsumer == null) {
                  System.out.println("Invalid username");
                  System.out.println("Returning back to main menu");
              } else {
                  currentModerator.modifyUser(globalModerators, selectedConsumer);
              }
          } else if (input.equals("vg")) {
              System.out.println("# of groups:  " + groups.size());
              System.out.println("Public Groups: ");
              for (Group g : groups) {
                  if (g.getSearchable()) {
                      System.out.println("   " + g.getGroupName());
                  }
              }
              if (current.getModeratorStatus()) {
                  System.out.println("You are a global moderator.");
              }
              System.out.println("v: view");
              System.out.println("c: create");
              if (current.getModeratorStatus()) {
                  System.out.println("d: delete group");
              }
              System.out.println("i: accept group invites");
              System.out.println("q: back to main menu");
              input = s.nextLine();
              boolean validGroup = false;
              if (input.equalsIgnoreCase("v")) {
                  System.out.println("Which group would you like to view?");
                  for (Group g : groups) {
                      if (g.getSearchable()) {
                          System.out.println(g.getGroupName());
                      }
                  }
                  input = s.nextLine();
                  Group viewedGroup = null;
                  for (Group g : groups) {
                      if (g.getGroupName().equalsIgnoreCase(input)) {
                          viewedGroup = g;
                          validGroup = true;
                      }
                  }
                  if (validGroup) {
                      while (true) {
                          boolean owner = false;
                          boolean isModerator = false;
                          for (int i = 0; i < viewedGroup.getModerators().size(); i++) {
                              if (current.equals(viewedGroup.getModerators().get(i).getModerator())) {
                                  isModerator = true;
                                  break;
                              }
                          }
                          if (current.equals(viewedGroup.getOwner().getModerator())) {
                              owner = true;
                          }
                          if (isModerator) {
                              if (owner) {
                                  viewedGroup.metrics();
                                  System.out.println("You are the owner of this group.");
                                  System.out.println("d: demote moderator");
                                  System.out.println("o: give ownership to another moderator");
                                  System.out.println("m: make member moderator");
                              } else {
                                  viewedGroup.metrics();
                                  System.out.println("You are a moderator in this group.");
                              }
                              System.out.println("c: change group settings");
                              System.out.println("g: clear/modify group description");
                              System.out.println("v: view group posts");
                              System.out.println("i: invite");
                              System.out.println("a: accept join requests");
                              System.out.println("r: remove member");
                              System.out.println("l: leave group");
                              System.out.println("q: to stop viewing this group");
                              input = s.nextLine();
                              if (viewedGroup.getOwner().getModerator().equals(current) && input.equalsIgnoreCase("d")) {
                                  System.out.println("Enter the name of the moderator you would like to demote: ");
                                  boolean notAModerator = true;
                                  input = s.nextLine();
                                  for (Moderator m : viewedGroup.getModerators()) {
                                      if (m.getModerator().getUsername().equalsIgnoreCase(input)) {
                                          notAModerator = false;
                                      }
                                  }
                                  if (notAModerator) {
                                      System.out.println("Cannot demote a regular user, or this user does not exist.");
                                  } else {
                                      for (int j = 0; j < viewedGroup.getModerators().size(); j++) {
                                          if (viewedGroup.getModerators().get(j).getModerator().getUsername().equalsIgnoreCase(input)) {
                                              viewedGroup.demoteModerator(viewedGroup.getModerators().get(j));
                                          }
                                      }
                                  }
                              } else if (input.equalsIgnoreCase("v")) {
                                  viewedGroup.viewPosts(s, null, current);
                              } else if (input.equalsIgnoreCase("l")) {
                                  System.out.println("y or n: Are you sure you want to leave the group?");
                                  input = s.nextLine();
                                  if (input.equalsIgnoreCase("y")) {
                                      viewedGroup.leaveGroup(current);
                                  } else {
                                      System.out.println("You are still a member of the group.");
                                  }
                              } else if (input.equalsIgnoreCase("i")) {
                                  System.out.println("Input the username of who you would like to invite: ");
                                  boolean foundUser = false;
                                  input = s.nextLine();
                                  for (Consumer c : Users) {
                                      if (c.getUsername().equalsIgnoreCase(input)) {
                                          foundUser = true;
                                          viewedGroup.inviteUser(c);
                                          c.addGroupInvite(viewedGroup);
                                      }
                                  }
                                  if (!foundUser) {
                                      System.out.println("User does not exist in the database.");
                                  }
                              } else if (input.equalsIgnoreCase("c")) {
                                  System.out.println("What would you like to change the group name to?");
                                  input = s.nextLine();
                                  boolean duplicateName = false;
                                  for (Group g : groups) {
                                      if (g.getGroupName().equals(input)) {
                                          duplicateName = true;
                                          break;
                                      }
                                  }
                                  if (input.equalsIgnoreCase(viewedGroup.getGroupName())) {
                                      System.out.println("Your group already has that name.");
                                  } else if (duplicateName) {
                                      System.out.println("Another group already has that name.");
                                  } else {
                                      viewedGroup.changeGroupName(input, current);
                                  }
                                  viewedGroup.changeSettings();
                              } else if (input.equalsIgnoreCase("r")) {
                                  System.out.println("Enter the name of the person you wish to remove: ");
                                  input = s.nextLine();
                                  boolean removedUser = false;
                                  Consumer match = null;
                                  for (Consumer c : viewedGroup.getGroupMembers()) {
                                      if (c.getUsername().equalsIgnoreCase(input)) {
                                          removedUser = true;
                                          match = c;
                                      }
                                  }
                                  if (!removedUser) {
                                      System.out.println("Unable to remove user, either not in group or they are a moderator.");
                                  } else if (removedUser) {
                                      viewedGroup.delete(match);
                                      match.getGroupMembership().remove(viewedGroup);
                                  }
                              } else if (input.equalsIgnoreCase("a")) {
                                  System.out.print("Join Requests: ");
                                  for (Consumer c : viewedGroup.getJoinRequests()) {
                                      System.out.print(c.getUsername() + " ");
                                  }
                                  System.out.println("\nEnter the name of the person you wish to accept: ");
                                  input = s.nextLine();
                                  for (Consumer c : viewedGroup.getJoinRequests()) {
                                      if (input.equalsIgnoreCase(c.getUsername())) {
                                          viewedGroup.acceptJoinRequest(c);
                                      }
                                  }
                              } else if (input.equalsIgnoreCase("g")) {
                                  viewedGroup.clear();
                              } else if (input.equalsIgnoreCase("m")) {
                                  System.out.println("Enter the name of the member you wish to make moderator: ");
                                  input = s.nextLine();
                                  for (int j = 0; j < viewedGroup.getGroupMembers().size(); j++) {
                                      if (viewedGroup.getGroupMembers().get(j).getUsername().equalsIgnoreCase(input)) {
                                          viewedGroup.makeModerator(viewedGroup.getGroupMembers().get(j));
                                      }
                                  }
                              } else if (input.equalsIgnoreCase("q")) {
                                  System.out.println("Exiting view of group: " + viewedGroup.getGroupName());
                                  return;
                              } else if (viewedGroup.getOwner().getModerator().equals(current) && input.equalsIgnoreCase("o")) {
                                  System.out.println("Enter the name of the moderator you would like to give ownership to");
                                  System.out.println("q: leave");
                                  input = s.nextLine();
                                  if (input.equalsIgnoreCase("q")) {
                                      System.out.println("Returning to group view");
                                  } else {
                                      boolean regularUser = false;
                                      Moderator found = null;
                                      for (Consumer c : viewedGroup.getGroupMembers()) {
                                          if (c.getUsername().equalsIgnoreCase(input)) {
                                              regularUser = true;
                                          }
                                      }
                                      if (!regularUser) {
                                          for (Moderator m : viewedGroup.getModerators()) {
                                              if (m.getModerator().getUsername().equals(input)) {
                                                  found = m;
                                              }
                                              if (found == null) {
                                                  System.out.println("Inputted user is not a moderator or does not exist.");
                                              } else {
                                                  viewedGroup.makeOwner(found);
                                              }
                                          }
                                      }
                                  }
                              }
                          } else if (viewedGroup.getGroupMembers().contains(current)) {
                              viewedGroup.metrics();
                              System.out.println("You are a member of the group.");
                              System.out.println("m: make a group post");
                              System.out.println("v: view group posts");
                              System.out.println("l: leave group");
                              System.out.println("q: to stop viewing this group");
                              input = s.nextLine();
                              if (input.equalsIgnoreCase("m")) {
                                  System.out.println("Enter Post: ");
                                  String post = s.nextLine();
                                  viewedGroup.addPost(post, current);
                                  System.out.println();
                                  System.out.println("Post added successfully");
                                  System.out.println();
                                  System.out.println();
                              } else if (input.equalsIgnoreCase("v")) {
                                  viewedGroup.viewPosts(s, null, current);
                              } else if (input.equalsIgnoreCase("l")) {
                                  System.out.println("y or n: Are you sure you want to leave the group?");
                                  input = s.nextLine();
                                  if (input.equalsIgnoreCase("y")) {
                                      viewedGroup.leaveGroup(current);
                                  } else {
                                      System.out.println("You are still a member of the group.");
                                  }
                              } else if (input.equalsIgnoreCase("q")) {
                                  System.out.println("Exiting view of group: " + viewedGroup.getGroupName());
                                  return;
                              }
                             } else if (!viewedGroup.getGroupMembers().contains(current) && !viewedGroup.getModerators().contains(current)) {
                                 viewedGroup.metrics();
                                 System.out.println("You are not a member of the group.");
                                 System.out.println("v: view posts");
                                 System.out.println("j: join group");
                                 System.out.println("q: to stop viewing this group");
                                 input = s.nextLine();
                                 if (input.equalsIgnoreCase("j")) {
                                  viewedGroup.requestJoin(current);
                                  if (current.getGroupMembership().contains((viewedGroup))) {
                                      current.addGroupMembership(viewedGroup);
                                  }
                              } else if (input.equalsIgnoreCase("v")) {
                                     viewedGroup.viewPosts(s, null, current);
                              } else if (input.equalsIgnoreCase("q")) {
                                  System.out.println("Exiting view of group: " + viewedGroup.getGroupName());
                                  return;
                              }
                          }

                      }
                  } else {
                      System.out.println("Invalid group name");
                  }
              } else if (input.equalsIgnoreCase("c")) {
                  boolean groupExists = false;
                  System.out.println("What is the name of the group you would like to create?");
                  String groupName = s.nextLine();
                  for (Group g : groups) {
                      if (g.getGroupName().equalsIgnoreCase(groupName)) {
                          groupExists = true;
                          break;
                      }
                  }
                  if (!groupExists) {
                      groups.add(new Group(groupName, new Moderator(current)));
                      System.out.println("New group \"" + groupName + "\" has been created.");
                      System.out.println("Enter a description for the group, can also leave it blank: ");
                      input = s.nextLine();
                      String description = input;
                      System.out.println("Would you like it to be listed publicly in the group view?\ny or n");
                      input = s.nextLine();
                      if (input.equalsIgnoreCase("y")) {
                          for (Group g : groups) {
                              if (g.getGroupName().equals(groupName)) {
                                  g.isSearchable(true);
                                  g.addMember(current);
                                  g.makeModerator(current);
                                  g.setDescription(description);
                                  current.addGroupMembership(g);
                                  System.out.println("Group \"" + groupName + "\" will be listed.");
                              }
                          }
                      } else if (input.equalsIgnoreCase("n")) {
                          for (Group g : groups) {
                              if (g.getGroupName().equals(groupName)) {
                                  g.isSearchable(false);
                                  g.addMember(current);
                                  g.makeModerator(current);
                                  g.setDescription(description);
                                  System.out.println("Group " + groupName + " will not be listed.");
                              }
                          }
                      } else {
                          System.out.println("Invalid input, group will be defaulted to public.");
                          for (Group g : groups) {
                              if (g.getGroupName().equals(groupName)) {
                                  g.isSearchable(true);
                                  g.addMember(current);
                                  g.makeModerator(current);
                                  System.out.println("Group \"" + groupName + "\" will be listed.");
                              }
                          }
                      }
                  } else {
                      System.out.println("Cannot create group, one already exists with this name.");
                  }
              } else if (input.equalsIgnoreCase("i")) {
                  System.out.println("Invites: ");
                  if (current.getGroupInvites().isEmpty()) {
                      System.out.println("   No Invites");
                      System.out.println("Hit enter to go back");
                  } else {
                      for (Group g : current.getGroupInvites()) {
                          System.out.println("   " + g.getGroupName());
                      }
                      System.out.println("Enter the group name to accept the invite: ");
                  }
                  input = s.nextLine();
                  if (!current.getGroupInvites().isEmpty()) {
                      for (int i = 0; i < current.getGroupInvites().size(); i++) {
                          if (current.getGroupInvites().get(i).getGroupName().equals(input)) {
                              for (int j = 0; j < groups.size(); j++) {
                                  groups.get(j).acceptedInvite(current);
                                  current.acceptGroupInvite(groups.get(j));
                              }
                          }
                      }
                  }
              } else if (current.getModeratorStatus() && input.equalsIgnoreCase("d")) {
                  System.out.println("Input the name of the group you would like to delete: ");
                  input = s.nextLine();
                  Group selectedGroup = null;
                  for (Group g : groups) {
                      if (input.equalsIgnoreCase(g.getGroupName())) {
                          selectedGroup = g;
                      }
                  }
                  if (selectedGroup == null) {
                      System.out.println("No groups are deleted.");
                      System.out.println("Returning to main menu");
                  } else {
                      if (!selectedGroup.getInvited().isEmpty()) {
                          for (int i = 0; i < Users.size(); i++) {
                              if (Users.get(i).getGroupInvites().contains(selectedGroup)) {
                                  Users.get(i).getGroupInvites().remove(selectedGroup);
                              }
                              if (Users.get(i).getGroupMembership().contains(selectedGroup)) {
                                  Users.get(i).getGroupMembership().remove(selectedGroup);
                              }
                          }
                      }
                      currentModerator.deleteGroup(groups, selectedGroup);
                  }
              } else {
                  System.out.println("Returning to main menu");
              }

          } else if (input.equals("EXIT")) {
                  System.exit(0);
              }


              }
          }
          public static boolean validateInfo (String username, String password){
              for (Consumer s : Users) {
                  current = s;
                  if (s.getUsername().equals(username) && s.getPassword().equals(password)) {
                      for (Moderator gm : globalModerators) {
                          if (gm.getModerator().getUsername().equals(current.getUsername())) {
                              current.setGlobalModerator(true);
                              currentModerator = new Moderator(s);
                              break;
                          }
                      }
                      return true;
                  }
              }
              return false;
          }
          public static void logout () {
              home = true;
              current = null;
          }
          public static void tryExit (String p, Scanner s){
              if (p.equals("EXIT")) {
                  s.close();
                  System.exit(0);
              }
          }

      }
