package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;

/** Indicates the User in the model has logged out*/
public class UserLoggedOutEvent extends BaseEvent {
    @Override
    public String toString() {
        return "Logged out";
    }
}
