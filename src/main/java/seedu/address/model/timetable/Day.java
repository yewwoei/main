package seedu.address.model.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.regex.Pattern;

/**
 * Represents a Day of the week.
 * Guarantees: immutable; is valid as declared in {@link #isValidDay(String)}
 */
public class Day {

    public static final String MESSAGE_DAY_CONSTRAINTS =
            "Days are case insensitive and should be one of the following: Mon, Tue, Wed, Thu, Fri, Sat, Sun";

    public static final String DAY_VALIDATION_REGEX = "((mon|tue|wed|thu|fri|sat|sun))$";

    public final String value;

    /**
     * Constructs a {@code Day}.
     *
     * @param day A valid phone number.
     */
    public Day(String day) {
        requireNonNull(day);
        checkArgument(isValidDay(day), MESSAGE_DAY_CONSTRAINTS);
        value = day;
    }
    /**
     * Returns true if a given string is a valid Day.
     */
    public static boolean isValidDay(String test) {
        Pattern testHelper = Pattern.compile(DAY_VALIDATION_REGEX, Pattern.CASE_INSENSITIVE);
        return testHelper.asPredicate().test(test);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Day // instanceof handles nulls
                && value.equals(((Day) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
