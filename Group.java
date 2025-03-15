import java.util.ArrayList;

public class Group  {
    private boolean searchable;
    private String name;
    private ArrayList<Consumer> groupMembers = new ArrayList<Consumer>();
    private ArrayList<Moderator> moderators = new ArrayList<Moderator>(); // change to Moderator object when class is created

    public Group(String name) {
        this.name = name;
    }
    public String getGroupName() {
        return name;
    }

    public void changeGroupName(String name) {
        this.name = name;
    }

    public ArrayList<Consumer> getGroupMembers() {
        return groupMembers;
    }

    public void addMember(Consumer consumer) {
        groupMembers.add(consumer);
        System.out.println(consumer.getUsername() + " is added to the group.");
    }

    public void makeModerator(Consumer consumer) {
        if(!groupMembers.contains(consumer)) {
            System.out.println("Group member does not exist.");
        } else {
            Moderator newModerator = new Moderator(consumer);
            moderators.add(newModerator);
            System.out.println(consumer.getUsername() + " is now a moderator.");
        }
    }


}
