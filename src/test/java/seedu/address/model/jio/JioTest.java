package seedu.address.model.jio;

import static org.junit.Assert.*;
import static seedu.address.model.jio.JioTestUtil.DINNER;
import static seedu.address.model.jio.JioTestUtil.JANE;
import static seedu.address.model.jio.JioTestUtil.LUNCH;
import static seedu.address.model.jio.JioTestUtil.LUNCH_COPY;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import seedu.address.model.Name;
import seedu.address.model.restaurant.Address;
import seedu.address.model.timetable.Date;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Time;
import seedu.address.model.timetable.Week;
import seedu.address.model.user.*;

class JioTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void hasRestaurant_restaurantNotInAddressBook_returnsFalse() {
        assertFalse(LUNCH.hasUser(JANE));
    }

    @Test
    public void hasRestaurant_restaurantInAddressBook_returnsTrue() {
        LUNCH.addUser(JANE);
        assertTrue(LUNCH.hasUser(JANE));
    }


    @Test
    void addUser() {
        assertFalse(DINNER.hasUser(JANE));
        DINNER.addUser(JANE);
        assertTrue(DINNER.hasUser(JANE));
    }

    @Test
    void setCreator() { 
        LUNCH.setCreator(JANE);
        assertEquals(LUNCH.getCreator(), JANE.getUsername());
    }

    @Test
    void equals() {
        // same values -> returns true
        assertTrue(LUNCH.equals(LUNCH_COPY));

        // same object -> returns true
        assertTrue(LUNCH.equals(LUNCH));

        // null -> returns false
        assertFalse(LUNCH.equals(null));

        // different type -> returns false
        assertFalse(LUNCH.equals(5));

        // different restaurant -> returns false
        assertFalse(LUNCH.equals(DINNER));
    }

}
