package seedu.address.model.jio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.model.jio.JioTestUtil.DINNER;
import static seedu.address.model.jio.JioTestUtil.LUNCH;
import static seedu.address.model.jio.JioTestUtil.LUNCH_COPY;

import org.junit.jupiter.api.Test;

class JioTest {

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