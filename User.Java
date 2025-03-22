public abstract class User implements Manageable{
    public abstract void changeSettings();
    public abstract void clear();
    public abstract void metrics();
    public abstract void delete();
    public abstract String getUsername();
    public abstract String getPassword();
    public abstract ArrayList<Group> getGroupInvites();
    public abstract void message(User user);
    public abstract void requestJoin(Group group);
    public abstract void usersOnline();
    public abstract void addGroupInvite(Group group);
}
