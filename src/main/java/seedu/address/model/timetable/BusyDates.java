package seedu.address.model.timetable;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import seedu.address.model.user.Username;

/**
 * Represents a user's busy schedule in the MakanBook.
 * Guarantees: data values are validated and immutable.
 */
public class BusyDates {

    // Identity fields
    private final Username username;

    // Data fields
    private final HashMap<Week, List<Date>> dates;

    /**
     * Every field must be present and not null.
     */
    public BusyDates(Username username, HashMap<Week, List<Date>> dates) {
        requireAllNonNull(username, dates);
        this.username = username;
        // sorting the dates.
        dates.forEach((key, value) -> Collections.sort(value));
        this.dates = dates;
    }

    public Username getUsername() {
        return this.username;
    }

    public List<Date> getBusyDatesForWeek(Week week) {
        // the TreeSet<Date> for the week.
        List<Date> weekDates = dates.get(week);
        return Collections.unmodifiableList(weekDates);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getUsername())
                .append(" Timetable:\n");

        this.dates.forEach((key, dateList) ->
                dateList.forEach(builder::append));

        return builder.toString();
    }
}
