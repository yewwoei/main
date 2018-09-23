package seedu.address.model.user;

import org.junit.Assert;
import org.junit.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class UserFriendListTest {
    @Test
    public void listFriendsTest() {
        User alice = new User(new Name("Alice"), new Phone("8942"), new Email("alice@g.com"), new Address("6 Baker"));
        User bob = new User(new Name("Bob"), new Phone("8942"), new Email("bob@g.com"), new Address("6 Baker"));
        alice.addFriend(bob);
        System.out.println(alice.listFriends());
        Assert.assertEquals(alice.listFriends() , "Bob\n");
        Assert.assertEquals(bob.listFriends() , "Alice\n");
    }

    @Test
    public void multipleListFriends() {
        User alice = new User(new Name("Alice"), new Phone("8942"), new Email("alice@g.com"), new Address("6 Baker"));
        User bob = new User(new Name("Bob"), new Phone("8942"), new Email("bob@g.com"), new Address("6 Baker"));
        User carol = new User(new Name("Carol"), new Phone("8942"), new Email("carol@g.com"), new Address("6 Baker"));
        alice.addFriend(bob);
        carol.addFriend(alice);
        Assert.assertEquals(alice.listFriends() , "carol\nbob\n");
        Assert.assertEquals(bob.listFriends() , "Alice\n");
        Assert.assertEquals(carol.listFriends() , "Alice\n");
    }
}
