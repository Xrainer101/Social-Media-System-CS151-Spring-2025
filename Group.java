import java.util.ArrayList;

public class Group  {
    private boolean searchable = true;
    private String name;
    private Moderator owner;
    private ArrayList<Consumer> groupMembers = new ArrayList<Consumer>();
    private ArrayList<Moderator> moderators = new ArrayList<Moderator>();
    private ArrayList<Consumer> joinRequests = new ArrayList<Consumer>();
    private ArrayList<Consumer> invited = new ArrayList<Consumer>();

    public Group(String name, Moderator owner) {
        this.name = name;
        this.owner = owner;
    }
    public String getGroupName() {
        return name;
    }

    public Moderator getOwner() {
        return owner;
    }

    public ArrayList<Consumer> getJoinRequests() {
        return joinRequests;
    }

    public ArrayList<Consumer> getGroupMembers() {
        return groupMembers;
    }

    public ArrayList<Moderator> getModerators() {
        return moderators;
    }

    public boolean getSearchable() {
        return searchable;
    }

    public void isSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public void setOwner(Moderator owner) {
        this.owner = owner;
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
        boolean isMod = false;
        for(Moderator m: moderators) {
            if(m.getModerator().equals(current)) {
                isMod = true;
            }
        }
        if (!joinRequests.contains(current) && !groupMembers.contains(current)) {
            joinRequests.add(current);
        } else if (joinRequests.contains(current)) {
            System.out.println("You already sent a request to join.");
        } else if (groupMembers.contains(current) || isMod) {
            System.out.println("You are already in the group.");
        }
    }

    public void acceptJoinRequest(Consumer consumer) {
            joinRequests.remove(consumer);
            groupMembers.add(consumer);
            System.out.println(consumer.getUsername() + " is accepted into the group.");
    }

    public void sendInvite(Consumer consumer) {
        boolean alreadyInvited = false;
        for (Consumer c : groupMembers) {
            if (c.equals(consumer)) {
                alreadyInvited = true;
            }
        }
        for (Moderator m : moderators) {
            if (m.getModerator().equals(consumer)) {
                alreadyInvited = true;
            }
        }
        for (Consumer c : invited) {
            if (c.equals(consumer)) {
                alreadyInvited = true;
            }
        }
        if (alreadyInvited) {
            System.out.println("User is ineligible for an invite.");
        } else {
            System.out.println("Sent a group invite to: " + consumer.getUsername());
            invited.add(consumer);
        }
    }

    public void addMember(Consumer consumer) {
        groupMembers.add(consumer);
        System.out.println(consumer.getUsername() + " is added to the group.");
    }

    public void removeMember(Consumer consumer) {
        removeMember(consumer);
        System.out.println(consumer.getUsername() + " is removed from the group.");
    }

    public void metrics() {
        System.out.println("Viewing: " + getGroupName());
        System.out.print("Moderators: ");
        for (Moderator m: moderators) {
            System.out.print(m.getModerator().getUsername() + " ");
        }
        System.out.print("\nMembers: ");
        for (Consumer c: groupMembers) {
            System.out.print(c.getUsername() + " ");
        }
        System.out.println();
    }

    public void makeModerator(Consumer consumer) {
        boolean isModerator = false;
        for(Moderator m: moderators) {
            if (m.getModerator().equals(consumer)) {
                isModerator = true;
            }
        }
        if (isModerator) {
            System.out.println(consumer.getUsername() + " is already a moderator.");
        } else if(!groupMembers.contains(consumer)) {
            System.out.println("Group member does not exist.");
        } else {
            Moderator newModerator = new Moderator(consumer);
            groupMembers.remove(consumer);
            moderators.add(newModerator);
            System.out.println(consumer.getUsername() + " is now a moderator.");
        }
    }

    public void demoteModerator(Moderator moderator) {
            groupMembers.add(moderator.getModerator());
            System.out.println(moderator.getModerator().getUsername() + " is now a regular member.");
            moderators.remove(moderator);
    }

}
