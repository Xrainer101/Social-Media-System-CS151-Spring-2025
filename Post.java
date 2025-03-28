import javax.naming.LimitExceededException;
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
        Consumer from;
    public Comment(String message, Consumer from) {
       this.message = message;
       this.from = from;
    }
    }
    public void addLike() {
          likes += 1;
    }
    public void setDescription(String p) {
        this.description = p;
    }
    public void addComment(Scanner s, Consumer friend) {
        System.out.println("Enter Comment: ");
        String description = s.nextLine();
        Comment c = new Comment(description, friend);
        try {
            maxComments();
        } catch (LimitExceededException e) {
            System.out.println(e.getMessage());
            return;
        }
        comments.add(c);
    }

    public void maxComments() throws LimitExceededException {
        if (comments.size() >= 100) {
            throw new LimitExceededException("LimitExceededException: Cannot comment, post has maximum number of comments.");
        }
    }

    public String getDescription() {
         return description;
    }
    public void checkDescriptionException(String [] words) throws InvalidDescriptionException {
       if(words.length > 30) {
          throw new InvalidDescriptionException("This post has too many words!");
       }
       else if(words.length == 1) {
             int count = 0;
            for(int i = 0; i < words[0].length(); i++) {
               if(Character.isDigit(words[0].charAt(i))) {
                  count +=1;
               }
            }
            if(count == words[0].length()) {
                throw new InvalidDescriptionException("Post can't be just a number!");
            }
       }

    }
    public boolean checkDescriptionString(String description) {
        try {
        String [] words = description.split(" ");
        checkDescriptionException(words);
        return true;
        }
        catch(InvalidDescriptionException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
    
    public void viewPost (Scanner s, Consumer friend) {
        //mode is based on if you are looking at your posts or friends post
        System.out.println("Post");
        System.out.println(description);
        System.out.println("Comments: ");
        for(Comment comment: comments) {
            System.out.println(comment.message);
            System.out.println("From: " + comment.from.getUsername());
        }
      
        if (friend != null) {
            System.out.println("add comment (y/n): ");
            String input = s.nextLine();
            if(input.equals("y")) {
                addComment(s, friend);
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
