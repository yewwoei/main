package seedu.address.model.group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.user.User;

/**
 * Group class for users to create and join groups.
 * No two groups can have the same name.
 * Groups can only be joined by invitation only unlike jios.
 */
public class Group {
    private String groupName;
    private ArrayList<User> acceptedUsers;
    private ArrayList<User> pendingUsers;

    public Group(String groupName, User creator) {
        this.groupName = groupName;
        this.acceptedUsers = new ArrayList<>();
        acceptedUsers.add(creator);
        this.pendingUsers = new ArrayList<>();
    }

    public Group(String groupName, User creator, User... users) {
        this.groupName = groupName;
        this.acceptedUsers = new ArrayList<>();
        acceptedUsers.add(creator);
        List<User> toAdd = Arrays.asList(users);
        this.pendingUsers = new ArrayList<>(toAdd);
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
     * Gets the group name
     * @return groupName
     */
    public String getGroupName() {
        return groupName;
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
        return otherGroup.groupName.equals(groupName)
                && otherGroup.acceptedUsers.equals(acceptedUsers)
                && otherGroup.pendingUsers.equals(pendingUsers);
    }
}
