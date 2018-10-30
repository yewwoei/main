package seedu.address.model.util;

import seedu.address.model.Name;
import seedu.address.model.UserData;
import seedu.address.model.user.Email;
import seedu.address.model.user.Password;
import seedu.address.model.user.Phone;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleUserDataUtil {
    public static User[] getSampleUsers() {
        return new User[] {
        new User(new Username("navekom"), new Password("pwwd123"),
                new Name("Evan Mok"), new Phone("91234567"),
                new Email("evanm@gmail.com")),
        new User(new Username("meena567"), new Password("meenapwd123"),
                new Name("Meena"), new Phone("98834568"),
                new Email("meena567@gmail.com")),
        new User(new Username("chelchia"), new Password("chelpwd123"),
                new Name("Chelsea Chia"), new Phone("90134569"),
                new Email("chelchia@gmail.com")),
        new User(new Username("katespades"), new Password("katepwd123"),
                new Name("Kate Ng"), new Phone("91434567"),
                new Email("kateSpades@gmail.com")),
        new User(new Username("aideeeen"), new Password("yewwoei123"),
                new Name("Low Yew Woei"), new Phone("91765567"),
                new Email("yewwoei@gmail.com")),
        new User(new Username("themyth"), new Password("themyth2k18"),
                new Name("AP Damith"), new Phone("91134567"),
                new Email("damith@nus.edu.com")),
        new User(new Username("brotherLoong"), new Password("LHL2k18"),
                new Name("Hsien Loong"), new Phone("80232567"),
                new Email("leehl@pmo.com")),
        new User(new Username("thejrlinguist"), new Password("kengji123"),
                new Name("KengJi"), new Phone("95534567"),
                new Email("kengji@gmail.com"))
        };
    }

    public static UserData getSampleUserData() {
        UserData sampleUd = new UserData();
        for (User sampleUser : getSampleUsers()) {
            sampleUd.addUser(sampleUser);
        }
        return sampleUd;
    }
}
