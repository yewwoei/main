package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.group.Friendship;
import seedu.address.model.group.FriendshipStatus;
import seedu.address.model.user.User;

public class TypicalFriendships {
    static List<User> listUsers = TypicalUsers.getTypicalUsers();
    public static final Friendship FRIENDSHIP_1 = new FriendshipBuilder().build();
    public static final Friendship FRIENDSHIP_2 = new FriendshipBuilder()
            .withFriendshipStatus(FriendshipStatus.ACCEPTED)
            .withFriendUser(listUsers.get(2))
            .withMe(listUsers.get(3))
            .withInitiatedBy(listUsers.get(2))
            .build();
    public static final Friendship FRIENDSHIP_3 = new FriendshipBuilder()
            .withFriendshipStatus(FriendshipStatus.ACCEPTED)
            .withFriendUser(listUsers.get(3))
            .withMe(listUsers.get(2))
            .withInitiatedBy(listUsers.get(2))
            .build();

    private TypicalFriendships() {} // prevents instantiation

    public static List<Friendship> getTypicalFriendships() {
        return new ArrayList<>(Arrays.asList(FRIENDSHIP_1, FRIENDSHIP_2, FRIENDSHIP_3));
    }
}
