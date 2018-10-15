package seedu.address.model.jio;

import seedu.address.model.restaurant.Address;
import seedu.address.model.timetable.Date;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Time;
import seedu.address.model.timetable.Week;
import seedu.address.model.user.Name;

public class JioTestUtil {

    private static Name LUNCH_NAME = new Name("lunch");
    private static Date LUNCH_DATE = new Date(new Week("1"), new Day("mon"), new Time("1200"));
    private static Address LUNCH_ADDRESS = new Address("finefood");

    public static final Jio LUNCH = new Jio(LUNCH_NAME, LUNCH_DATE, LUNCH_ADDRESS);
    public static final Jio LUNCH_COPY = new Jio(LUNCH_NAME, LUNCH_DATE, LUNCH_ADDRESS);

    private static Name DINNER_NAME = new Name("dinner");
    private static Date DINNER_DATE = new Date(new Week("2"), new Day("tue"), new Time("1800"));
    private static Address DINNER_ADDRESS = new Address("foodclique");

    public static final Jio DINNER = new Jio(DINNER_NAME, DINNER_DATE, DINNER_ADDRESS);

}
