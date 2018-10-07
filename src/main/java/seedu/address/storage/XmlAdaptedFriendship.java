package seedu.address.storage;

import java.util.HashMap;
import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.user.Friendship;
import seedu.address.model.user.FriendshipStatus;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;

/**
 * JAXB-friendly version of Friendship
 */
public class XmlAdaptedFriendship {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "friendship's %s field is missing!";

    @XmlElement(required = true)
    private String friendUser;
    @XmlElement(required = true)
    private String me;
    @XmlElement(required = true)
    private String initiatedBy;

    /**
     * Constructs an XmlAdaptedFriendship.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedFriendship() {}

    /**
     * Constructs an {@code XmlAdaptedRestaurant} with the given restaurant details.
     */
    public XmlAdaptedFriendship(String friendUser, String initiatedBy, String me) {
        this.friendUser = friendUser;
        this.initiatedBy = initiatedBy;
        this.me = me;


    }

    /**
     * Converts a given Friendship into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdadptedUser
     */
    public XmlAdaptedFriendship(Friendship source) {
        friendUser = source.getFriendUser().getUsername().toString();
        initiatedBy = source.getInitiatedBy().getUsername().toString();
        me = source.getMe().getUsername().toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedFriendship)) {
            return false;
        }

        XmlAdaptedFriendship otherFriendship = (XmlAdaptedFriendship) other;
        return Objects.equals(initiatedBy, otherFriendship.initiatedBy)
                && Objects.equals(friendUser, otherFriendship.friendUser)
                && Objects.equals(me, otherFriendship.me);
    }

    public Friendship toModelType(HashMap<Username, User> usernameUserHashmap) throws IllegalValueException {
        if(friendUser == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Friendship.class.getSimpleName()));
        }

        if(me == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Friendship.class.getSimpleName()));
        }

        if(initiatedBy == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Friendship.class.getSimpleName()));
        }

        if(!usernameUserHashmap.containsKey(new Username(friendUser))) {
            throw new IllegalValueException("Friend User does not exist in storage.");
        }

        if(!usernameUserHashmap.containsKey(new Username(me))) {
            throw new IllegalValueException("User does not exist in storage.");
        }

        if(!friendUser.equals(me) || !friendUser.equals(friendUser)) {
            throw new IllegalValueException("Friendship is not initiated by either party in Friendship");
        }

        return new Friendship(usernameUserHashmap.get(new Username(friendUser)),
                usernameUserHashmap.get(new Username(initiatedBy)),
                usernameUserHashmap.get(new Username(me)), FriendshipStatus.ACCEPTED);
    }
}
