package seedu.address.model.group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.user.User;

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

    public void addMembers(User... users) {
        List<User> toAdd = Arrays.asList(users);
        toAdd.forEach(user -> {pendingUsers.add(user);
        user.addGroupRequest(this);});
    }

    public String getGroupName() {
        return groupName;
    }

    public List<User> getAcceptedUsers() {
        return acceptedUsers;
    }

    public List<User> getPendingUsers() {
        return pendingUsers;
    }

    public void changeMemberStatus(User user) {
        pendingUsers.remove(user);
        acceptedUsers.add(user);
    }

    public void removePendingUser(User user) {
        pendingUsers.remove(user);
    }

    public void removeAcceptedUser(User user) {
        acceptedUsers.remove(user);
    }

    @Override
    public boolean equals(Object other) {
        Group otherGroup = (Group) other;
        return otherGroup.groupName.equals(groupName)
                && otherGroup.acceptedUsers.equals(acceptedUsers)
                && otherGroup.pendingUsers.equals(pendingUsers);
    }
}
