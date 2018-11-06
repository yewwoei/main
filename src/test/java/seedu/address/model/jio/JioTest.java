package seedu.address.model.jio;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.model.jio.JioTestUtil.DINNER;
import static seedu.address.model.jio.JioTestUtil.JANE;
import static seedu.address.model.jio.JioTestUtil.LUNCH;
import static seedu.address.model.jio.JioTestUtil.LUNCH_COPY;

public class JioTest {

    @Test
    public void hasUser_userNotJoinedJio_returnsFalse() {
        assertFalse(LUNCH.hasUser(JANE));
    }

    @Test
    public void hasUser_userJoinedJio_returnsTrue() {
        LUNCH.addUser(JANE);
        assertTrue(LUNCH.hasUser(JANE));
    }


    @Test
    public void addUser() {
        assertFalse(DINNER.hasUser(JANE));
        DINNER.addUser(JANE);
        assertTrue(DINNER.hasUser(JANE));
    }

    @Test
    public void setCreator() {
        LUNCH.setCreator(JANE);
        assertEquals(LUNCH.getCreator(), JANE.getUsername());
    }

    @Test
    public void equals() {
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