package seedu.address.storage;

import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Friendship;
import seedu.address.model.group.FriendshipStatus;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;

/**
 * JAXB-friendly version of Friendship
 */
public class XmlAdaptedFriendship {
    private static final Logger logger = LogsCenter.getLogger(XmlAdaptedFriendship.class);
    public static final String MESSAGE_WRONG_INITIATION = "Friendship initiated by third party!";
    public static final String MESSAGE_MISSING_FIELD = "friendship's %s field is missing!";

    @XmlElement(required = true)
    private String friendUser;
    @XmlElement(required = true)
    private String me;
    @XmlElement(required = true)
    private String initiatedBy;
    @XmlElement(required = true)
    private String friendshipStatus;

    /**
     * Constructs an XmlAdaptedFriendship.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedFriendship() {}

    /**
     * Constructs an {@code XmlAdaptedFriendship} with the given friendship details.
     */
    public XmlAdaptedFriendship(String friendUser, String initiatedBy, String me, String friendshipStatus) {
        this.friendUser = friendUser;
        this.initiatedBy = initiatedBy;
        this.me = me;
        this.friendshipStatus = friendshipStatus;
    }

    /**
     * Converts a given Friendship into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdadptedFriendship
     */
    public XmlAdaptedFriendship(Friendship source) {
        friendUser = source.getFriendUser().getUsername().toString();
        initiatedBy = source.getInitiatedBy().getUsername().toString();
        me = source.getMe().getUsername().toString();
        friendshipStatus = source.getFrienshipStatus().toString();
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

    /**
     * Constructs friendships from xml data
     * @param usernameUserHashmap hashmap of usernames to users
     * @return constructed friendship
     * @throws IllegalValueException
     */
    public Friendship toModelType(HashMap<Username, User> usernameUserHashmap) throws IllegalValueException {
        if (friendUser == null) {
            throw new IllegalValueException(String.format(MESSAGE_MISSING_FIELD,
                    Friendship.class.getSimpleName()));
        }

        if (me == null) {
            throw new IllegalValueException(String.format(MESSAGE_MISSING_FIELD,
                    Friendship.class.getSimpleName()));
        }

        if (initiatedBy == null) {
            throw new IllegalValueException(String.format(MESSAGE_MISSING_FIELD,
                    Friendship.class.getSimpleName()));
        }

        if (friendshipStatus == null) {
            throw new IllegalValueException(String.format(MESSAGE_MISSING_FIELD,
                    Friendship.class.getSimpleName()));
        }

        if (!initiatedBy.equals(me) && !initiatedBy.equals(friendUser)) {
            System.out.println("me is :" + me);
            System.out.println("friendUser is:" + friendUser);
            throw new IllegalValueException(MESSAGE_WRONG_INITIATION);
        }

        return new Friendship(usernameUserHashmap.get(new Username(friendUser)),
                usernameUserHashmap.get(new Username(initiatedBy)),
                usernameUserHashmap.get(new Username(me)), FriendshipStatus.valueOf(friendshipStatus));
    }
}
