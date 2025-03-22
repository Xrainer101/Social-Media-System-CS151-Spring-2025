import java.util.Scanner;
import java.util.ArrayList;
public class Post {
    private int likes = 0;
    private ArrayList<Comment> comments;
    private String description;
    public Post(String description) {
        this.description = description;
        comments = new ArrayList<Comment> ();

    }
    private class Comment {
        String message;
        String name;
    public Comment(String message, String name) {
       this.message = message;
       this.name = name;
    }
    }
    public void addLike() {
          likes += 1;
    }
    public void setDescription(String p) {
         this.description = p;
    }
    public void addComment(Scanner s, String name) {
        System.out.println("Enter Comment: ");
        String description = s.nextLine();
        Comment c = new Comment(description, name);
        comments.add(c);
    }
    public String getDescription() {
         return description;
    }

    public void viewPost (Scanner s, Consumer friend) {
        //mode is based on if you are looking at your posts or friends post
        System.out.println("Post");
        System.out.println(description);
        System.out.println("Comments: ");
        for(Comment comment: comments) {
            System.out.println(comment.message);
            System.out.println("From: " + comment.name);
        }
      
        if (friend != null) {
            System.out.println("add comment (enter y to add): ");
            String input = s.nextLine();
            if(input.equals("y")) {
                addComment(s, friend.getUsername());
            }
            System.out.println("like post (y/n)");
            input = s.nextLine();
            if(input.equals("y")) {
                addLike();
            }

        }
        System.out.println("Hit Enter to go back:");
        String p = s.nextLine();
        System.out.println("Going back");
    }
    public int getLikes() {
        return likes;
    }

}
