package seedu.address.model.jio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.restaurant.Address;
import seedu.address.model.timetable.Date;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Time;
import seedu.address.model.timetable.Week;
import seedu.address.model.user.Email;
import seedu.address.model.user.Password;
import seedu.address.model.user.Phone;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;
import seedu.address.testutil.TypicalUsers;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

/**
 * Sample jios for testing.
 */
public class JioTestUtil {
    public static final User JANE = new User(new Username("jane"), new Password("password"),
            new seedu.address.model.Name("jane"), new Phone("12345678"), new Email("jane@gmail.com"));

    private static seedu.address.model.Name lunchName = new seedu.address.model.Name("lunch");
    private static Date lunchDate = new Date(new Week("1"), new Day("mon"), new Time("1200"));
    private static Address lunchAddress = new Address("finefood");
    private static Username creator = TypicalUsers.getTypicalUsers().get(0).getUsername();
    private static List<Username> people = new ArrayList<>(Arrays.asList(
            TypicalUsers.getTypicalUsers().get(0).getUsername(),
            TypicalUsers.getTypicalUsers().get(1).getUsername(),
            TypicalUsers.getTypicalUsers().get(2).getUsername()
    ));

    public static final Jio LUNCH = new Jio(lunchName, lunchDate, lunchAddress, people, creator);
    public static final Jio LUNCH_COPY = new Jio(lunchName, lunchDate, lunchAddress, people, creator);

    private static seedu.address.model.Name dinnerName = new seedu.address.model.Name("dinner");
    private static Date dinnerDate = new Date(new Week("2"), new Day("tue"), new Time("1800"));
    private static Address dinnerAddress = new Address("foodclique");

    public static final Jio DINNER = new Jio(dinnerName, dinnerDate, dinnerAddress, creator);

//    public static final String VALID_NAME_LUNCH = "lunch";
//    public static final String VALID_WEEK_LUNCH = "reading";
//    public static final String VALID_DAY_LUNCH = "mon";
//    public static final String VALID_TIME_LUNCH = "1200";
//    public static final String VALID_ADDRESS_LUNCH = "Waa Cow";
//
//    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
//    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
//    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
//    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
//    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
//    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
//    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
//    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

}
