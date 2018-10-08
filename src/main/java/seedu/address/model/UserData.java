package seedu.address.model;

import java.util.HashMap;

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
    
    public void setHashMap(HashMap<Username, User> updatedHashMap) {
        usernameUserHashMap = updatedHashMap;
    }

    public HashMap<Username, User> getUsernameUserHashMap() {
        return usernameUserHashMap;
    }
}
