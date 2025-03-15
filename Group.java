import java.util.ArrayList;

public class Group  {
    private boolean searchable;
    private String name;
    private ArrayList<Consumer> groupMembers = new ArrayList<Consumer>();
    private ArrayList<Moderator> moderators = new ArrayList<Moderator>();
    private ArrayList<Consumer> joinRequests = new ArrayList<Consumer>();

    public Group(String name) {
        this.name = name;
    }
    public String getGroupName() {
        return name;
    }

    public ArrayList<Consumer> getJoinRequests() {
        return joinRequests;
    }

    public ArrayList<Consumer> getGroupMembers() {
        return groupMembers;
    }

    public boolean getSearchable() {
        return searchable;
    }

    public void isSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public void changeGroupName(String name, Consumer current) {
        boolean nameChanged = false;
        for(Moderator moderator: moderators) {
            if (current.equals(moderator.getModerator())); {
                this.name = name;
                nameChanged = true;
                System.out.println("Group name has been changed to " + name);
                break;
            }
        }
        if (!nameChanged) {
            System.out.println("Only moderators can change the group name.");
        }
    }

    public void requestJoin(Consumer current) {
        if (!joinRequests.contains(current) && !groupMembers.contains(current)) {
            joinRequests.add(current);
        } else if (joinRequests.contains(current)) {
            System.out.println("You already sent a request to join.");
        } else if (groupMembers.contains(current)) {
            System.out.println("You are already in the group.");
        }
    }

    public void joinRequestList() {

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
