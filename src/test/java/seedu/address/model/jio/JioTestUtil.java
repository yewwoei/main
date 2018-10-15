package seedu.address.model.jio;

import seedu.address.model.restaurant.Address;
import seedu.address.model.timetable.Date;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Time;
import seedu.address.model.timetable.Week;
import seedu.address.model.user.Name;

public class JioTestUtil {

    private static Name lunchName = new Name("lunch");
    private static Date lunchDate = new Date(new Week("1"), new Day("mon"), new Time("1200"));
    private static Address lunchAddress = new Address("finefood");

    public static final Jio LUNCH = new Jio(lunchName, lunchDate, lunchAddress);
    public static final Jio LUNCH_COPY = new Jio(lunchName, lunchDate, lunchAddress);

    private static Name dinnerName = new Name("dinner");
    private static Date dinnerDate = new Date(new Week("2"), new Day("tue"), new Time("1800"));
    private static Address dinnerAddress = new Address("foodclique");

    public static final Jio DINNER = new Jio(dinnerName, dinnerDate, dinnerAddress);

}
