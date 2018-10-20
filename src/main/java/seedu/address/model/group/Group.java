package seedu.address.model.group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Name;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;

/**
 * Group class for users to create and join groups.
 * No two groups can have the same name.
 * Groups can only be joined by invitation only unlike jios.
 */
public class Group {
    private Name groupName;
    private List<User> acceptedUsers;
    private List<User> pendingUsers;
    private List<Username> pendingUsernames;

    public Group(Name groupName) {
        this.groupName = groupName;
        this.acceptedUsers = new ArrayList<>();
        this.pendingUsers = new ArrayList<>();
    }

    public Group(Name groupName, User creator) {
        this.groupName = groupName;
        this.acceptedUsers = new ArrayList<>();
        acceptedUsers.add(creator);
        this.pendingUsers = new ArrayList<>();
    }

    public Group(Name groupName, User creator, User... users) {
        this.groupName = groupName;
        this.acceptedUsers = new ArrayList<>();
        acceptedUsers.add(creator);
        List<User> toAdd = Arrays.asList(users);
        this.pendingUsers = new ArrayList<>(toAdd);
    }

    public Group(Name groupName, List<User> acceptedUsers, List<User> pendingUsers) {
        this.groupName = groupName;
        this.acceptedUsers = acceptedUsers;
        this.pendingUsers = pendingUsers;
    }

    public Group(Name groupName, List<Username> pendingUsers) {
        this.groupName = groupName;
        this.pendingUsernames = pendingUsers;
    }

    public void addCreator(User user) {
        this.acceptedUsers.add(user);
    }

    public List<Username> getPendingUsernames() {
        return pendingUsernames;
    }

    /**
     * Allows any user to add members into the group after creation of group
     * @param users
     */
    public void addMembers(User... users) {
        List<User> toAdd = Arrays.asList(users);
        toAdd.forEach(user -> {
            pendingUsers.add(user);
            user.addGroupRequest(this);
        });
    }

    /**
     * Allows any user to add members into the group after creation of group
     * @param listUsers
     */
    public void addMembers(List<User> listUsers) {
        listUsers.forEach(user -> {
            pendingUsers.add(user);
            user.addGroupRequest(this);
        });
    }

    /**
     * Gets the group name
     * @return groupName
     */
    public String getGroupName() {
        return groupName.toString();
    }

    /**
     * Gets the list of Users who have accepted the invitation to join the group
     * @return acceptedUsers
     */
    public List<User> getAcceptedUsers() {
        return acceptedUsers;
    }

    /**
     * Gets the list of Users who have yet to accept the invitation to join the group
     * @return pendingUsers
     */
    public List<User> getPendingUsers() {
        return pendingUsers;
    }

    /**
     * Removes the User from the pendingUsers list.
     * Adds the User to the acceptedUsers list.
     * @param user User
     */
    public void changeMemberStatus(User user) {
        pendingUsers.remove(user);
        acceptedUsers.add(user);
    }

    /**
     * Removes the User from the pendingUsers list.
     * @param user User
     */
    public void removePendingUser(User user) {
        pendingUsers.remove(user);
    }

    /**
     * Adds the User to the acceptedUsers list.
     * @param user User
     */
    public void removeAcceptedUser(User user) {
        acceptedUsers.remove(user);
    }

    /**
     * Overrides the equals method
     * @param other Object
     */
    @Override
    public boolean equals(Object other) {
        Group otherGroup = (Group) other;
        return otherGroup.groupName.equals(groupName);
    }
}
