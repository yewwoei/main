package seedu.address.model;

import java.util.HashMap;

import javafx.collections.ObservableList;
import seedu.address.model.group.Group;
import seedu.address.model.jio.Jio;
import seedu.address.model.jio.UniqueJioList;
import seedu.address.model.user.Password;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;

/**
 * Wraps all data for User Data.
 */
public class UserData {

    private HashMap<Username, User> usernameUserHashMap;
    private HashMap<Name, Group> groupHashMap;
    //private List<Jio> jios;
    private UniqueJioList jios;

    public UserData() {
        usernameUserHashMap = new HashMap<>();
        groupHashMap = new HashMap<>();
        jios = new UniqueJioList();
    }

    public HashMap<Username, User> getUsernameUserHashMap() {
        return usernameUserHashMap;
    }

    public HashMap<Name, Group> getGroupHashmap() {
        return groupHashMap;
    }

    public ObservableList<Jio> getJios() {
        return jios.asUnmodifiableObservableList();
    }

    public boolean hasUser(Username username) {
        return usernameUserHashMap.containsKey(username);
    }

    /**
     * Returns true if the {@code Password} matches that of the user with {@code Username}.
     */
    public boolean verifyLogin(Username username, Password password) {
        User userToCheck = usernameUserHashMap.get(username);
        return userToCheck.getPassword().equals(password);
    }

    public void addUser(User user) {
        usernameUserHashMap.put(user.getUsername(), user);
    }

    public void addUser(Username username, User user) {
        usernameUserHashMap.put(username, user);
    }

    public User getUser(Username username) {
        return usernameUserHashMap.get(username);
    }

    public void removeUser(User user) {
        usernameUserHashMap.remove(user.getUsername());
    }

    //=========== Friend methods ============================================================================
    public boolean hasGroup(Name groupName) {
        return groupHashMap.containsKey(groupName);
    }

    public void addGroup(Group group) {
        groupHashMap.put(group.getGroupName(), group);
    }

    //=========== Jio methods ===============================================================================
    public boolean hasJioName(Name jioName) {
        return jios.asUnmodifiableObservableList().stream().anyMatch(jio -> jio.getName().equals(jioName));
    }

    public boolean hasJio(Jio j) {
        return jios.asUnmodifiableObservableList().stream().anyMatch(jio -> jio.equals(j));
    }

    public void addJio(Jio jio) {
        jios.add(jio);
    }

    public void removeJioOfName(Name jioName) {
        jios.asUnmodifiableObservableList().removeIf(jio -> jio.getName().equals(jioName));
    }

    public boolean isUserInJioOfName(Name jioName, User user) {
        return jios.asUnmodifiableObservableList().stream().anyMatch(jio
                -> (jio.getName().equals(jioName) && jio.hasUser(user)));
    }

    /**
     * Adds user to the specified jio. Assumes check for the existence of jio already done.
     */
    public void addUserToJioOfName(Name jioName, User user) {
        jios.asUnmodifiableObservableList().stream().forEach(jio -> {
            if (jio.getName().equals(jioName)) {
                jio.addUser(user);
            }
        });
    }

    /**
     * Checks if the specified user is the creator of the jio with the specified name.
     * @param jioName name of jio to be checked
     * @param user current user
     * @return True if the user is the creator of jio, false otherwise.
     */
    public boolean isCreatorOfJio(Name jioName, User user) {
        return jios.asUnmodifiableObservableList().stream().anyMatch(
            jio -> (jio.getName().equals(jioName) && jio.getCreator().equals(user.getUsername())));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UserData)) {
            return false;
        }
        UserData otherUserData = (UserData) other;
        return other == this // short circuit if same object
                || usernameUserHashMap.equals(otherUserData.getUsernameUserHashMap());
    }

    @Override
    public int hashCode() {
        return usernameUserHashMap.hashCode();
    }
}
