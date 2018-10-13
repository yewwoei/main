package seedu.address.model.group;

import org.junit.Assert;
import org.junit.Test;

import seedu.address.model.user.Email;
import seedu.address.model.user.Name;
import seedu.address.model.user.Password;
import seedu.address.model.user.Phone;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;

public class UserGroupListTest {
    @Test
    public void createGroup() {
        User alice = new User(new Username("Alice"), new Password("password"), new Name("Alice"),
                new Phone("8942"), new Email("alice@g.com"));
        alice.createGroup("My Lonely Club");
        Assert.assertEquals(alice.listGroups() , "My Lonely Club\n");
        Assert.assertEquals(alice.listGroupRequests() , "");
    }
}
