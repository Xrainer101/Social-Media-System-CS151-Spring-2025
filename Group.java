import java.util.ArrayList;

public class Group  {
    private boolean searchable;
    private String name;
    private ArrayList<Consumer> groupMembers = new ArrayList<Consumer>();
    private ArrayList<Consumer> moderators = new ArrayList<Consumer>(); // change to Moderator object when class is created

    public Group(String name) {
        this.name = name;
    }
    public String getGroupName() {
        return name;
    }

    public ArrayList<Consumer> getGroupMembers() {
        return groupMembers;
    }

    public void addMember(Consumer consumer) {
        groupMembers.add(consumer);
        System.out.println(consumer.getUsername() + " is added to the group.");
    }

    }

