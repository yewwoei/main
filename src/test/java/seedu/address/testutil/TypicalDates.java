package seedu.address.testutil;

import seedu.address.model.timetable.Date;

/**
 * A utility class containing a list of {@code Date} objects to be used in tests.
 */
public class TypicalDates {

    public static final Date DATE_A = new DateBuilder().build();
    public static final Date DATE_B = new DateBuilder().withWeek("15").build();
    public static final Date DATE_C = new DateBuilder().withTime("0600").build();
    public static final Date DATE_D = new DateBuilder().withDay("Tue").withTime("1330").withWeek("reading").build();

}
