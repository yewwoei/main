package seedu.address.model.jio;

import seedu.address.model.Model;
import seedu.address.model.Name;
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

/**
 * Contains helper final Strings for testing Jio.
 */
public class JioTestUtil {

    public static final User JOHN = new User(new Username("john"), new Password("password"), new Name("john"),
            new Phone("12345678"), new Email("john@gmail.com"));
    public static final User JANE = new User(new Username("jane"), new Password("password"), new Name("jane"),
            new Phone("12345678"), new Email("jane@gmail.com"));

    private static Name lunchName = new Name("lunch");
    private static Date lunchDate = new Date(new Week("1"), new Day("mon"), new Time("1200"));
    private static Address lunchAddress = new Address("finefood");
    private static Username creator = JOHN.getUsername();

    public static final Jio LUNCH = new Jio(lunchName, lunchDate, lunchAddress, creator);
    public static final Jio LUNCH_COPY = new Jio(lunchName, lunchDate, lunchAddress, creator);

    private static Name dinnerName = new Name("dinner");
    private static Date dinnerDate = new Date(new Week("2"), new Day("tue"), new Time("1800"));
    private static Address dinnerAddress = new Address("foodclique");

    public static final Jio DINNER = new Jio(dinnerName, dinnerDate, dinnerAddress, creator);

    /**
     * Updates {@code model}'s filtered list to show only the restaurant at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showAllJio(Model model) {
        model.updateFilteredJioList(jio -> true);
    }

}
