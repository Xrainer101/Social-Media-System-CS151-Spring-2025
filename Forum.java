import java.util.ArrayList;
import java.util.Scanner;
public class Forum {
    static ArrayList<Consumer> Users = new ArrayList<Consumer>();
    private static ArrayList<Group> groups = new ArrayList<Group>();
    private static Consumer current = null;
    static boolean home = true;
   public static void main(String[] args) {
       Scanner s = new Scanner(System.in);
       Consumer current = null;
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
        if(input.equals("S")) {
            System.out.println("Enter username: ");
            String username = s.nextLine();
            System.out.println("Enter password: ");
            String password = s.nextLine();
            Users.add(new Consumer(username, password));
            System.out.println();
            System.out.println("Signup successful");
            System.out.println();
            System.out.println();
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
        else if(input.equals("EXIT")) {
            System.exit(0);
        }
        
     }
     
   }
   // please add a printline statement in the same format and an if statement.
   // to implement functionality. 
   public static void functionality(Scanner s) {
       boolean done = false;
      while(!done) {
          System.out.println("mp: Make post");
          System.out.println("vp: viewPosts");
          System.out.println("vf: viewFriend");
          System.out.println("af: add friend");
          System.out.println("vg: view groups");
          System.out.println("lo: logout");
          System.out.println("EXIT: exits program(loses all data)");
          System.out.println("Enter input(2 character): ");
          String input = s.nextLine();
         if(input.equals("vg")) {
            System.out.println("There are " + groups.size() + " groups.");
            System.out.println("Public Groups: ");
            for (Group g: groups) {
                if (g.getSearchable()) {
                    System.out.println(g.getGroupName());
                }
            }
            System.out.println("v: view\nc: create");
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
                    viewedGroup.metrics();
                    boolean owner = false;
                    for (int i = 0; i < viewedGroup.getModerators().size(); i++) {
                        if (current.equals(viewedGroup.getOwner().getModerator())) {
                            owner = true;
                    }//for (Moderator mn : viewedGroup.getModerators()) {
                    if (owner) {
                        if (viewedGroup.getOwner().getModerator().equals(current)) {
                            System.out.println("You are the owner of this group.");
                            System.out.println("d: demote moderator");
                        } else {
                            System.out.println("You are a moderator of the group.");
                        }
                        System.out.println("l: leave group");
                        System.out.println("i: invite");
                        System.out.println("c: change group name");
                        System.out.println("r: remove member");
                        System.out.println("a: accept join requests");
                        System.out.println("m: make member moderator");
                        input = s.nextLine();
                        if (viewedGroup.getOwner().getModerator().equals(current) && input.equalsIgnoreCase("d")) {
                            System.out.println("Enter the name of the moderator you would like to demote: ");
                            boolean notAModerator = false;
                            input = s.nextLine();
                            for (Consumer c: viewedGroup.getGroupMembers()) {
                                if (c.getUsername().equalsIgnoreCase(input)) {
                                    notAModerator = true;
                                }
                            }
                            if (notAModerator) {
                                System.out.println("Cannot demote a regular user.");
                            } else if (!notAModerator) {
                               /* for (Moderator mo: viewedGroup.getModerators()) {
                                   if (mo.getModerator().getUsername().equalsIgnoreCase(input)) {
                                       viewedGroup.demoteModerator(mo);
                                   }
                                } */
                                for(int j = 0; i < viewedGroup.getModerators().size(); i++) {
                                    if (viewedGroup.getModerators().get(i).getModerator().getUsername().equalsIgnoreCase(input)) {
                                        viewedGroup.demoteModerator(viewedGroup.getModerators().get(i));
                                    }
                                }
                            }
                        } else if (input.equalsIgnoreCase("l")) {
                            System.out.println("y or n: Are you sure you want to leave the group?");
                            if(input.equalsIgnoreCase("y")) {
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
                                    viewedGroup.sendInvite(c);
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
                                }
                            }
                            if (input.equalsIgnoreCase(viewedGroup.getGroupName())) {
                                System.out.println("Your group already has that name.");
                            } else if (duplicateName) {
                                System.out.println("Another group already has that name.");
                            } else {
                                viewedGroup.changeGroupName(input, current);
                            }
                        } else if (input.equalsIgnoreCase("r")) {
                            System.out.println("Enter the name of the person you wish to remove: ");
                            input = s.nextLine();
                            boolean removedUser = false;
                            for(Consumer c: viewedGroup.getGroupMembers()) {
                                if (c.getUsername().equalsIgnoreCase(input)) {
                                    viewedGroup.removeMember(c);
                                    removedUser = true;
                                }
                            }
                            if (!removedUser) {
                                System.out.println("Unable to remove user, either not in group or they are a moderator.");
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
                        } else if (input.equalsIgnoreCase("m")) {
                            System.out.println("Enter the name of the member you wish to make moderator: ");
                            input = s.nextLine();
                            for (int j = 0; j < viewedGroup.getGroupMembers().size(); j++) { // Consumer c : viewedGroup.getGroupMembers())
                                if (viewedGroup.getGroupMembers().get(i).getUsername().equalsIgnoreCase(input)) {
                                    viewedGroup.makeModerator(viewedGroup.getGroupMembers().get(i));
                                }
                            }
                        }
                    } else if (viewedGroup.getGroupMembers().contains(current)) {
                        System.out.println("You are a member of the group.");
                        System.out.println("l: leave group");
                        input = s.nextLine();
                        if (input.equalsIgnoreCase("l")) {
                            System.out.println("y or n: Are you sure you want to leave the group?");
                            input = s.nextLine();
                            if(input.equalsIgnoreCase("y")) {
                                viewedGroup.leaveGroup(current);
                            } else {
                                System.out.println("You are still a member of the group.");
                            }
                        }
                    } else if (!viewedGroup.getGroupMembers().contains(current) && !viewedGroup.getModerators().contains(current)) {
                        System.out.println("You are not a member of the group.");
                        System.out.println("j: join group");
                        input = s.nextLine();
                        if (input.equalsIgnoreCase("j")) {
                            viewedGroup.requestJoin(current);
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
                for(Group g: groups) {
                    if (g.getGroupName().equalsIgnoreCase(groupName)) {
                        groupExists = true;
                    }
                }
                if (!groupExists) {
                    groups.add(new Group(groupName, new Moderator(current)));
                    System.out.println("New group \"" + groupName + "\" has been created.");
                    System.out.println("Would you like it to be listed publicly in the group view?\ny or n");
                    input = s.nextLine();
                    if (input.equalsIgnoreCase("y")) {
                        for (Group g : groups) {
                            if (g.getGroupName().equals(groupName)) {
                                g.isSearchable(true);
                                g.addMember(current);
                                g.makeModerator(current);
                                System.out.println("Group \"" + groupName + "\" will be listed.");
                            }
                        }
                    } else if (input.equalsIgnoreCase("n")) {
                        for (Group g : groups) {
                            if (g.getGroupName().equals(groupName)) {
                                g.isSearchable(false);
                                g.addMember(current);
                                g.makeModerator(current);
                                System.out.println("Group " + groupName + " will not be listed.");
                            }
                        }
                    } else {
                        System.out.println("Invalid Input");
                    }
                } else {
                  System.out.println("Cannot create group, one already exists with this name.");
                }
                } else {
                System.out.println("Invalid Input");
            }
         }
         else if(input.equals("lo")) {
            logout();
            done = true;
         }
         else if(input.equals("EXIT")) {
            System.exit(0);
         }
          
       }
   }
   public static boolean validateInfo(String username, String password) {
          for(Consumer s: Users) {
             if(s.getUsername().equals(username) && s.getPassword().equals(password) ) {
                 current = s;
                 return true;
             }
          }
          return false;
   }
   public static void logout() {
      home = true; 
      current = null;
   }

}