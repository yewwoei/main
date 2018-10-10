package seedu.address.model;

import java.util.HashMap;

import seedu.address.model.user.Password;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;

/**
 * Wraps all data for User Data.
 */
public class UserData {

    private HashMap<Username, User> usernameUserHashMap;

    public UserData() {
        usernameUserHashMap = new HashMap<>();
    }

    public HashMap<Username, User> getUsernameUserHashMap() {
        return usernameUserHashMap;
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
}
