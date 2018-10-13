package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.timetable.UniqueBusySchedule;

/**
 * JAXB-friendly adapted version of the user's list of busy Dates..
 */
public class XmlAdaptedBusySchedule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "The BusyDates' %s field is missing!";

    @XmlElement(required = true)
    private String username; // user that the blocked date belongs to.

    @XmlElement
    private List<XmlAdaptedDate> dates = new ArrayList<XmlAdaptedDate>();

    /**
     * Constructs an XmlAdaptedBusyDates.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedBusySchedule() {}

    /**
     * Constructs a {@code XmlAdaptedBusyDates} with the given username and dates.
     */
    public XmlAdaptedBusySchedule(String username, List<XmlAdaptedDate> dates) {
        this.username = username;
        if (dates != null) {
            this.dates = new ArrayList<>(dates);
        }
    }


    /**
     * Converts a given user's UniqueBusySchedule into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedBusyDate.
     */
    public XmlAdaptedBusySchedule(UniqueBusySchedule source) {
        this.username = source.getUsername().toString();

    }

    /**
     * Helper method to obtain a List<XmlAdaptedDate> from a UniqueBusySchedule
     */
    private List<XmlAdaptedDate> retrieveDateList(UniqueBusySchedule source) {
        List<XmlAdaptedDate> retrievedDates = new ArrayList<>();


    }


    /**
     * Converts this jaxb-friendly adapted tag object into the model's Date object that is blocked out in the user's
     * timetable.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted restaurant
     */
    public UniqueBusySchedule toModelType() throws IllegalValueException {
        return null;

    }

}
