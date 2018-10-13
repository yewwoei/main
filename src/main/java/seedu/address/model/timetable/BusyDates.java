package seedu.address.model.timetable;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

import seedu.address.model.user.Username;

/**
 * Represents a user's busy schedule in the MakanBook.
 * Guarantees: data values are validated and immutable.
 */
public class BusyDates {

    // Identity fields
    private final Username username;

    // Data fields
    private final HashMap<Week, TreeSet<Date>> dates;

    /**
     * Every field must be present and not null.
     */
    public BusyDates(Username username, HashMap<Week, TreeSet<Date>> dates) {
        requireAllNonNull(username, dates);
        this.username = username;
        this.dates = dates;
    }

    public Username getUsername() {
        return this.username;
    }

    public Iterator<Date> getBusyDatesForWeek(Week week) {
        // the TreeSet<Date> for the week.
        TreeSet<Date> weekDates = dates.get(week);
    }
}
