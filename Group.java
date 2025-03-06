import java.util.ArrayList;

public class Group  {
    private boolean searchable;
    private String name;
    private ArrayList<Consumer> groupMembers = new ArrayList<Consumer>();

    public Group(String name, ArrayList<> groupMembers) {
        this.name = name;
        this.groupMembers = groupMembers;
    }
    public String getGroupName() {
        return name;
    }

    public ArrayList<Consumer> getGroupMembers() {
        return groupMembers;
    }
}
