package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.timetable.BusyDates;
import seedu.address.model.timetable.Date;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Time;
import seedu.address.model.timetable.Week;

/**
 * JAXB-friendly adapted version of the user's list of busy Dates..
 */
public class XmlAdaptedBusyDates {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "The BusyDates' %s field is missing!";

    @XmlElement(required = true)
    private String username; // user that the blocked date belongs to.
    @XmlElement(required = true)
    private HashMap<String, List<XmlAdaptedBlockedDate>>  busyDates = new HashMap<String, List<XmlAdaptedBlockedDate>>();

    /**
     * Constructs an XmlAdaptedBusyDates.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedBusyDates() {}

    /**
     * Constructs a {@code XmlAdaptedBusyDates} with the given username and dates.
     */
    public XmlAdaptedBusyDates(String username, List<XmlAdaptedBlockedDate> dates) {} {
        this.username = username;
        if (dates != null) {
            this.dates = new ArrayList<>(dates);
        }
    }

    /**
     * Converts a given list of busy dates {@code List<Dates>} into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedTimeBlock
     */
    public XmlAdaptedBusyDates(Date source) {
        this.week = source.getWeek().toString();
        this.day = source.getDay().toString();
        this.time = source.getTime().toString();
    }

    /**
     * Converts this jaxb-friendly adapted tag object into the model's Date object that is blocked out in the user's
     * timetable.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted restaurant
     */
    public BusyDates toModelType() throws IllegalValueException {

        if (week == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Week.class.getSimpleName()));
        }

        if (!Week.isValidWeek(week)) {
            throw new IllegalValueException(Week.MESSAGE_WEEK_CONSTRAINTS);
        }

        if (day == null) {
            throw new IllegalValueException(Day.MESSAGE_DAY_CONSTRAINTS);
        }

        if (!Day.isValidDay(day)) {
            throw new IllegalValueException(Day.MESSAGE_DAY_CONSTRAINTS);
        }

        if (time == null) {
            throw new IllegalValueException(Time.MESSAGE_TIME_CONSTRAINTS);
        }

        if (!Time.isValidTime(time)) {
            throw new IllegalValueException(Time.MESSAGE_TIME_CONSTRAINTS);
        }

        // Create the objects for creating a date.

        final Week modelWeek = new Week(week);
        final Day modelDay = new Day(day);
        final Time modelTime = new Time(time);

        return new Date(modelWeek, modelDay, modelTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedTag)) {
            return false;
        }

        XmlAdaptedBlockedDate otherTime = (XmlAdaptedBlockedDate) other;
        return Objects.equals(week, otherTime.week)
                && Objects.equals(time, otherTime.time)
                && Objects.equals(day, otherTime.day);
    }
}
