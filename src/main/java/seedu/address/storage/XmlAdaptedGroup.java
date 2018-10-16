//package seedu.address.storage;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//import javax.xml.bind.annotation.XmlElement;
//
//import seedu.address.commons.exceptions.IllegalValueException;
//import seedu.address.model.group.Group;
//import seedu.address.model.jio.Jio;
//import seedu.address.model.user.User;
//
///**
// * JAXB-friendly version of Group.
// */
//public class XmlAdaptedGroup {
//    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";
//
//    @XmlElement(required = true)
//    private String groupName;
//    @XmlElement(required = true)
//    private String pendingUsers;
//    @XmlElement(required = true)
//    private String acceptedUsers;
//
//    /**
//     * Constructs an XmlAdaptedGroup.
//     * This is the no-arg constructor that is required by JAXB.
//     */
//    public XmlAdaptedGroup() {}
//
//    /**
//     * Constructs an {@code XmlAdaptedGroup} with the given group details.
//     */
//    public XmlAdaptedGroup(String groupName, String acceptedUsers, String pendingUsers) {
//        this.groupName = groupName;
//        this.acceptedUsers = acceptedUsers;
//        this.pendingUsers = pendingUsers;
//    }
//
//    /**
//     * Converts a given Jio into this class for JAXB use.
//     *
//     * @param source future changes to this will not affect the created XmlAdadptedGroup
//     */
//    public XmlAdaptedGroup(Group source) {
//        groupName = source.getGroupName();
//        acceptedUsers = UsernamesFromLists(source.getAcceptedUsers());
//        pendingUsers = UsernamesFromLists(source.getPendingUsers());
//    }
//
//    public String UsernamesFromLists(List<User> list) {
//        List<String> usernames = new ArrayList<>();
//        for (User u: list) {
//            usernames.add(u.getUsername().toString());
//        }
//        return usernames.toString();
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        if (!(other instanceof XmlAdaptedGroup)) {
//            return false;
//        }
//
//        XmlAdaptedGroup otherGroup = (XmlAdaptedGroup) other;
//        return Objects.equals(groupName, otherGroup.groupName);
//    }
//
//    /**
//     * Constructs groups from xml data
//     * @param usernameUserHashmap hashmap of usernames to users
//     * @return constructed friendship
//     * @throws IllegalValueException
//     */
//    public Group toModelType() throws IllegalValueException {
//        if (groupName == null) {
//
//        }
//
//    }
//
//}
//
