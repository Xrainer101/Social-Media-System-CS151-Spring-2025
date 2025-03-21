import java.util.ArrayList;
import java.util.Scanner;

public class Group implements Inviting, Manageable {
    private boolean searchable;
    private String name;
    private String description;
    private Moderator owner;
    private ArrayList<Consumer> groupMembers;
    private ArrayList<Moderator> moderators;
    private ArrayList<Consumer> joinRequests;
    private ArrayList<Consumer> invited;

    public Group(String name, Moderator owner) {
        this.name = name;
        this.owner = owner;
        this.description = "";
        groupMembers = new ArrayList<Consumer>();
        moderators = new ArrayList<Moderator>();
        joinRequests = new ArrayList<Consumer>();
        invited = new ArrayList<Consumer>();
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

    public ArrayList<Consumer> getInvited() {
        return invited;
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

    public void setDescription(String description) {
        this.description = description;
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
        if (!joinRequests.contains(current) && !groupMembers.contains(current) && !searchable) {
            joinRequests.add(current);
        } else if (joinRequests.contains(current) && !searchable) {
            System.out.println("You already sent a request to join.");
        } else if (groupMembers.contains(current) || isMod) {
            System.out.println("You are already in the group.");
        } else if (searchable && !groupMembers.contains(current) && !isMod) {
            addMember(current);
        }
    }

    public void acceptJoinRequest(Consumer consumer) {
            joinRequests.remove(consumer);
            groupMembers.add(consumer);
            System.out.println(consumer.getUsername() + " is accepted into the group.");
    }

    @Override
    public void inviteUser(Consumer consumer) {
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

    @Override
    public void delete(Consumer consumer) {
        groupMembers.remove(consumer);
        System.out.println(consumer.getUsername() + " is removed from the group.");
    }

    @Override
    public void metrics() {
        System.out.println("Viewing: \n   " + getGroupName());
        System.out.println("Description: \n   " + description);
        System.out.println("Moderators: ");
        if (moderators.isEmpty()) {
            System.out.println("   No moderators");
        } else {
            for (Moderator m : moderators) {
                System.out.println("   " + m.getModerator().getUsername());
            }
        }
        System.out.println("Members: ");
        if (groupMembers.isEmpty()) {
            System.out.println("   No members");
        } else {
            for (Consumer c : groupMembers) {
                System.out.println("   " + c.getUsername());
            }
        }
    }

    @Override
    public void changeSettings() {
        System.out.println("Would you like to change the privacy settings of the group?");
        System.out.println("y or n");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            System.out.println("Would you like to make the group public?");
            System.out.println("y: publicly listed and can be joined without requesting an invite.");
            System.out.println("n: not listed in public groups, join requests must also be accepted for users to enter.");
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                searchable = true;
                System.out.println("Group is public/kept public.");
            } else if (choice.equalsIgnoreCase("n")) {
                searchable = false;
                System.out.println("Group is private/kept private.");
            } else {
                System.out.println("Invalid input, returning to previous menu.");
            }
        } else if (choice.equalsIgnoreCase("n")) {
            System.out.println("Returning to previous menu, settings are not altered.");
        } else {
            System.out.println("Invalid input, returning to previous menu.");
        }
    }

    @Override
    public void clear() {
        System.out.println("Would you like to clear the group description?");
        System.out.println("y or n");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            description = "";
            System.out.println("Group description is cleared, would you like to add a new one?");
            System.out.println("y or n");
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                System.out.println("New description: ");
                choice = sc.nextLine();
                description = choice;
            } else if (choice.equalsIgnoreCase("n")) {
                System.out.println("Returning to previous menu, group description is left blank.");
            } else {
                System.out.println("Invalid input, returning to previous menu.");
            }
        } else if (choice.equalsIgnoreCase("n")) {
            System.out.println("Returning to previous menu, group description is not altered.");
        } else {
            System.out.println("Invalid input, returning to previous menu.");
        }
    }

    public void acceptedInvite(Consumer consumer) {
        invited.remove(consumer);
        groupMembers.add(consumer);
        System.out.println(consumer.getUsername() + " has accepted the group invite");
    }

    public void addMember(Consumer consumer) {
        groupMembers.add(consumer);
        System.out.println(consumer.getUsername() + " is added to the group.");
    }

    public void makeOwner(Moderator moderator) {
        boolean isOwner = false;
        if (owner.getModerator().getUsername().equals(moderator.getModerator().getUsername())) {
                isOwner = true;
        }
        if (isOwner) {
            System.out.println("You are already the owner.");
        } else if (!moderators.contains(moderator)) {
            System.out.println("Moderator does not exist");
        } else {
            owner = moderator;
            System.out.println(moderator.getModerator().getUsername() + " is the new owner of the group.");
        }
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
        } else if (!groupMembers.contains(consumer)) {
            System.out.println("Group member does not exist.");
        } else {
            Moderator newModerator = new Moderator(consumer);
            groupMembers.remove(consumer);
            moderators.add(newModerator);
            System.out.println(consumer.getUsername() + " is now a moderator.");
        }
    }

    public void demoteModerator(Moderator moderator) {
        if(!moderator.getModerator().getUsername().equals(owner.getModerator().getUsername())) {
            groupMembers.add(moderator.getModerator());
            System.out.println(moderator.getModerator().getUsername() + " is now a regular member.");
            moderators.remove(moderator);
        } else  if(moderator.getModerator().getUsername().equals(owner.getModerator().getUsername())) {
            if(moderators.size()>1) {
                moderators.remove(moderator);
                groupMembers.add(moderator.getModerator());
                owner = moderators.get(0);
                System.out.println("You have left the group, ownership is passed to " + moderators.get(0));
            } else if (moderators.size() <= 1) {
                System.out.println("You cannot demote yourself as the owner if there are no other moderators.");
            }
        }
    }

    public void leaveGroup(Consumer current) {
        boolean isModerator = false;
        Moderator temp = null;
        for (Moderator m: moderators) {
            if (m.getModerator().equals(current)) {
                temp = m;
                isModerator = true;
            }
        }
        if (owner.getModerator().equals(current)) {
            if(moderators.size()>1) {
                moderators.remove(temp);
                owner = moderators.get(0);
                System.out.println("You have left the group, ownership is passed to " + moderators.get(0).getModerator().getUsername());
            } else if (moderators.size() == 1) {
                System.out.println("There are no moderators to pass ownership to.");
            }
        } else if (isModerator && !owner.getModerator().equals(current)) {
            moderators.remove(temp);
            System.out.println("You have left the group.");
        } else if (groupMembers.contains(current)) {
            groupMembers.remove(current);
            System.out.println("You have left the group.");
        }
    }

}
