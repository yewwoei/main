package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.UserData;
import seedu.address.model.user.User;

/** Indicates the UserData in the model has changed*/
public class UserDataChangedEvent extends BaseEvent {

    public final UserData data;
    public final User user;

    public UserDataChangedEvent(UserData data, User user) {
        this.data = data;
        this.user = user;
    }

    @Override
    public String toString() {
        return "number of users " + data.getUsernameUserHashMap().size();
    }
}
