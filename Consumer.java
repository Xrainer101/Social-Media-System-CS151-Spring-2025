import javax.naming.LimitExceededException;
import java.util.ArrayList;
import java.util.Scanner;
public class Consumer extends Sharer {
   private String username;
   private String password;
   private ArrayList<Consumer> friends;
   private ArrayList<String> friendRequests;
   private ArrayList<Post> posts;
   private Messages messages;
   private ArrayList<Group> groupInvites;
   private ArrayList<Group> groups;
   private boolean isGlobalModerator;
   private Scanner scanner;

   public Consumer(String username, String password) {
      this.username = username;
      this.password = password;
      groupInvites = new ArrayList<Group>();
      groups = new ArrayList<Group>();
      friends = new ArrayList<Consumer>();
      friendRequests = new ArrayList<String>();
      posts = new ArrayList<Post>();
      messages = new Messages(this);
      isGlobalModerator = false;
   }
   
   public void setScanner(Scanner s) {
       scanner = s;
   }
   public String getUsername() {
      return username;
   }

   public String getPassword() {
      return password;
   }

   public boolean getModeratorStatus() {
      return isGlobalModerator;
   }

   public ArrayList<Group> getGroupMembership() {
      return groups;
   }

   public ArrayList<Group> getGroupInvites() {
      return groupInvites;
   }

   public void addGroupMembership(Group g) {
      groups.add(g);
   }

   public void setGlobalModerator(boolean moderatorStatus) {
      isGlobalModerator = moderatorStatus;
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
      } else {
         groupInvites.add(group);
      }
   }

   public void acceptGroupInvite(Group group) {
      boolean foundGroup = false;
      for (Group g : groupInvites) {
          if (group.getGroupName().equals(g.getGroupName())) {
              foundGroup = true;
              break;
          }
      }
      if (foundGroup) {
         groupInvites.remove(group);
         groups.add(group);
      } else {
         System.out.println(username + " is not in the group " + group.getGroupName());
      }
   }

   public Messages getMessages() {
       return messages;
   }

   public ArrayList<Post> getPosts() {
      return posts;
   }

   public void fullFriends() throws LimitExceededException {
       if (friends.size() >= 100) {
           throw new LimitExceededException("LimitExceededException: Cannot add/request, friends list is full at 100.");
       }
    }

    public void maximumPosts() throws LimitExceededException {
       if (posts.size() >= 100) {
           throw new LimitExceededException("LimitExceededException: Cannot add post, the maximum is 100.");
       }
    }

   public void addFriend(Consumer other) {
       try {
           fullFriends();
       } catch (LimitExceededException e) {
           System.out.println(e.getMessage());
           return;
       }
      this.friends.add(other);
   }

   public boolean addPost(String description) {
       try {
           maximumPosts();
       } catch (LimitExceededException e) {
           System.out.println(e.getMessage());
           return false;
       }
       Post post = new Post(description);
      if(!post.checkDescriptionString(description)) {
          return false;
      }
      else {
         this.posts.add(post);
         return true;
      }
   }

   public void viewPosts(Scanner s, Consumer user) {
      boolean done = false;
      while(!done) {
      for(int i = 0; i < posts.size(); i++) {
         System.out.print( i + ": " + posts.get(i).getDescription());
         System.out.println(" Likes: " + posts.get(i).getLikes());
      }
      System.out.println("Would you like to view more about a specific post (y/n)");
      String input = s.nextLine();
      Forum.tryExit(input,s);
       if(input.equals("y")) {
          boolean valid = false;
          Post target = null;
          while(!valid) {
          try {
             if(posts.size() > 0 ) {
             System.out.println("Enter index: ");
             String index = s.nextLine();
             int ind = Integer.parseInt(index);
             target = posts.get(ind);
             target.viewPost(s, user);
             valid = true;
             }
             else {
               System.out.println("This user has no posts to display");
               System.out.println("Going back");
               System.out.println();
               valid = true;
               done = true;
             }
          }
          catch(Exception e) {
            Forum.tryExit(input,s);
            System.out.println("Try again");
          }
          }
       } else {
          done = true;
       }

      }
   }

   public Post getPost(int i) {
    Post p = posts.get(i);
    return p;
   }

   public Consumer checkFriend(String Fusername) {
      for(Consumer f: friends) {
          if(f.getUsername().equals(Fusername)) {
             return f;
          }
      }
      return null;
   }
   public boolean checkRequests(Consumer r) {
         return friendRequests.contains(r.username);
   }
   public void addRequest(Consumer c) {
       try {
           fullFriends();
       } catch (LimitExceededException e) {
           System.out.println(e.getMessage());
           return;
       }
      friendRequests.add(c.username);
        
   }
   public ArrayList<String> getFriendsUsernames () {

      ArrayList<String> fUsernames = new ArrayList<String>();
      for(Consumer f: friends) {
         fUsernames.add(f.username);
      }
      return fUsernames;

   }

   @Override 
   public void edit() {
      boolean done = false;
      while(!done) {
            for(int i = 0; i < posts.size(); i++) {
                System.out.println(i  + ": " +posts.get(i).getDescription());
            }
            try {
                if(posts.size() > 0) {
                    System.out.println("Enter index of the post you want to edit: ");
                    int index = Integer.parseInt(scanner.nextLine());
                    Post currentPost = posts.get(index);
                    System.out.println("Old Post: " + currentPost.getDescription());
                    System.out.println("Enter edited post: ");
                    String editedPost = scanner.nextLine();
                    currentPost.setDescription(editedPost);
                    System.out.println(" Message edited successfully");
                    System.out.println();
                    done = true;
                }
                else {
                    System.out.println("No posts to edit");
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

   @Override 
   public void delete() {
      boolean done = false;
      while(!done) {
            for(int i = 0; i < posts.size(); i++) {
                System.out.println(i + ": " + posts.get(i).getDescription());
            }
            try {
                if(posts.size() > 0) {
                    System.out.println("Enter index of the post you want to delete: ");
                    int index = scanner.nextInt();
                    posts.remove(posts.get(index));
                    System.out.println(" Message deleted successfully");
                    System.out.println();
                    done = true;
                }
                else {
                    System.out.println("No posts to delete ");
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
   @Override 
   public void mostPopular() {
      if(posts.size() == 0) {
         System.out.println("no posts to display");
         System.out.println();
      }
      else {
         int max = posts.get(0).getLikes();
         Post maxPost = posts.get(0);
         for(int i = 0; i < posts.size(); i++) {
             if(posts.get(i).getLikes() > max) {
               max = posts.get(i).getLikes();
               maxPost = posts.get(i);
             }
         }
         System.out.println("This post has most likes: \n" + maxPost.getDescription());
         System.out.println("Likes: " + max);
         System.out.println();

      }
   }
}
