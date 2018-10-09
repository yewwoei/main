package seedu.address.model.timetable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.model.timetable.DateTestUtil.VALID_TIME_END;
import static seedu.address.model.timetable.DateTestUtil.VALID_TIME_MID;
import static seedu.address.model.timetable.DateTestUtil.VALID_TIME_START;
import static seedu.address.model.timetable.DateTestUtil.INVALID_TIME_END;
import static seedu.address.model.timetable.DateTestUtil.INVALID_TIME_MID;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TimeTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void nullTime_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        Time time = new Time(null);
    }
}
