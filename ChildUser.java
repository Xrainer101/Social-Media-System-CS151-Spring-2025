import java.util.ArrayList;
public class ChildUser extends User {
    private String username;
    private String password;
    private ArrayList<Group> groupInvites;

    public ChildUser(String username, String password) {
      this.username = username;
      this.password = password;
      groupInvites = new ArrayList<Group>();
    }

    
}
