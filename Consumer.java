import java.util.ArrayList;
import java.util.Scanner;
public class Consumer {
   private String username;
   private String password;
   private ArrayList<Consumer> friends;
   private ArrayList<String> friendRequests;
   private ArrayList<Post> posts;
   private Messages messages;
   public Consumer(String username, String password) {
      this.username = username;
      this.password = password;
      friends = new ArrayList<Consumer>();
      friendRequests = new ArrayList<String>();
      posts = new ArrayList<Post>();
      messages = new Messages(this);
   }
   public String getUsername() {
    return username;
   }
   public String getPassword() {
    return password;
   }
   
   
   public Messages getMessages() {
       return messages;
   }
   public void addFriend(Consumer other) {
      this.friends.add(other);
   }
   
   public void addPost(String description) {
      Post post = new Post(description);
      this.posts.add(post);
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
               System.out.println("This friend has no posts to display");
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

       }
       else {
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
      friendRequests.add(c.username);
        
   }
   public ArrayList<String> getFriendsUsernames () {

      ArrayList<String> fUsernames = new ArrayList<String>();
      for(Consumer f: friends) {
         fUsernames.add(f.username);
      }
      return fUsernames;

   }

}
