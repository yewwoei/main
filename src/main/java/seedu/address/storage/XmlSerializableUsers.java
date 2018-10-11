package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.UserData;
import seedu.address.model.accounting.Debt;
import seedu.address.model.jio.Jio;
import seedu.address.model.user.Friendship;
import seedu.address.model.user.User;

/**
 * An list of users that is serializable to XML format
 */
@XmlRootElement(name = "users")
public class XmlSerializableUsers {

    public static final String MESSAGE_DUPLICATE_PERSON = "User list contains duplicate User(s).";
    public static final String MESSAGE_NO_USER_FRIENDSHIP = "User required for friendship not found";
    public static final String MESSAGE_NO_USER_DEBTS = "User required for debts record not found";
    public static final String MESSAGE_DUPLICATE_JIO = "This jio already exists in the book";

    @XmlElement
    private List<XmlAdaptedUser> user;
    @XmlElement
    private List<XmlAdaptedFriendship> friendship;
    @XmlElement
    private List<XmlAdaptedDebt> debts;
    @XmlElement
    private List<XmlAdaptedJio> jios;

    /**
     * Creates an empty XmlSerializableUsers.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableUsers() {
        user = new ArrayList<>();
        friendship = new ArrayList<>();
        debts = new ArrayList<>();
        jios = new ArrayList<>();
    }

    /**
     * Conversion
     */
    public XmlSerializableUsers(UserData userData) {
        this();

        // adds Users into the hashmap
        userData.getUsernameUserHashMap().forEach((key, value) -> user
                .add(new XmlAdaptedUser(value)));
        // updates hashmap with friends of all Users
        userData.getUsernameUserHashMap().forEach((key, value) -> value.getFriends()
                .forEach(f -> friendship.add(new XmlAdaptedFriendship(f))));
        // updates hashmap with friendRequests of all Users
        userData.getUsernameUserHashMap().forEach((key, value) -> value.getFriendRequests()
                .forEach(f -> friendship.add(new XmlAdaptedFriendship(f))));
        userData.getUsernameUserHashMap().forEach((key, value) -> value.getDebts()
                .forEach(d -> debts.add(new XmlAdaptedDebt(d))));
        // updates jios list
        userData.getJios().forEach(jio -> jios.add(new XmlAdaptedJio(jio)));
    }

    /**
     * Converts this addressbook into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     * {@code XmlAdaptedRestaurant}.
     */
    public UserData toModelType() throws IllegalValueException {
        UserData userData = new UserData();
        for (XmlAdaptedUser u : user) {
            User user = u.toModelType();
            if (userData.hasUser(user.getUsername())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            userData.addUser(user);
        }

        for (XmlAdaptedFriendship f: friendship) {
            Friendship friendship = f.toModelType(userData.getUsernameUserHashMap());
            if (!userData.hasUser(friendship.getMe().getUsername())) {
                throw new IllegalValueException(MESSAGE_NO_USER_FRIENDSHIP);
            }
            if (!userData.hasUser(friendship.getFriendUser().getUsername())) {
                throw new IllegalValueException(MESSAGE_NO_USER_FRIENDSHIP);
            }

            userData.addUser(friendship.getMe().getUsername(),
                    userData.getUser(friendship.getMe().getUsername()).addFriendship(friendship));
        }

        for (XmlAdaptedDebt d: debts) {
            Debt debts = d.toModelType(userData.getUsernameUserHashMap());
            if (!userData.getUsernameUserHashMap().containsKey(debts.getCreditor().getUsername())) {
                throw new IllegalValueException(MESSAGE_NO_USER_DEBTS);
            }
            if (!userData.getUsernameUserHashMap().containsKey(debts.getDebtor().getUsername())) {
                throw new IllegalValueException(MESSAGE_NO_USER_DEBTS);
            }

            userData.getUsernameUserHashMap().put(debts.getCreditor().getUsername(),
                    userData.getUsernameUserHashMap().get(debts.getCreditor().getUsername()).addDebt(debts));

            userData.getUsernameUserHashMap().put(debts.getDebtor().getUsername(),
                    userData.getUsernameUserHashMap().get(debts.getDebtor().getUsername()).addDebt(debts));

        }

        for (XmlAdaptedJio j: jios) {
            Jio jio = j.toModelType();
            if (!userData.hasJio(jio)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_JIO);
            }
            userData.addJio(jio);
        }

        return userData;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlSerializableUsers)) {
            return false;
        }
        return user.equals(((XmlSerializableUsers) other).user);
    }
}
