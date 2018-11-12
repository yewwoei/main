package systemtests.timetable;

import static seedu.address.commons.core.Messages.MESSAGE_USER_NOT_LOGGED_IN_FOR_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import org.junit.Test;

import seedu.address.logic.commands.timetable.BlockDateCommand;
import seedu.address.model.Model;
import seedu.address.model.timetable.Date;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Time;
import seedu.address.model.timetable.Week;
import seedu.address.model.user.User;
import seedu.address.testutil.TypicalDates;
import seedu.address.testutil.UserBuilder;
import systemtests.AddressBookSystemTest;

public class BlockDateCommandSystemTest extends AddressBookSystemTest {

    private static final String INVALID_DAY = "9999";
    private static final String INVALID_TIME = "bla";
    private static final String INVALID_WEEK = "hello";
    private static final Date VALID_DATE_A = TypicalDates.DATE_A;
    private static final Date VALID_DATE_RECESS = TypicalDates.DATE_RECESS;
    private static final Date VALID_DATE_READING = TypicalDates.DATE_READING;
    private static final String VALID_WEEK = VALID_DATE_A.getWeek().toString();
    private static final String VALID_DAY = VALID_DATE_A.getDay().toString();
    private static final String VALID_TIME = VALID_DATE_A.getTime().toString();
    private static final User VALID_USER = new UserBuilder().withName("BOOYAH").withUsername("starfire").build();

    @Test
    public void block() {
        Model model = getModel();
        User currentUser = VALID_USER;

        Date toBlock = VALID_DATE_A;
        String command = createBlockDateCommand(toBlock);

        /* ------------------------ Perform valid block operations ----------------------------- */

        /* Case: User signs up, and attempts to block dates immediately. -> date blocked in user schedule. */
        model.addUser(currentUser);
        model.loginUser(currentUser);

        assertCommandSuccess(command, toBlock);
        model.freeDateForCurrentUser(toBlock);

        /* Case: user logs in normally and attempts to block normal dates -> date blocked in user schedule. */
        toBlock = VALID_DATE_RECESS;
        model.logoutUser();
        model.loginUser(currentUser);

        command = createBlockDateCommand(toBlock);
        assertCommandSuccess(command, toBlock);
        model.freeDateForCurrentUser(toBlock);

        /* Case: user logs in normally and attempts to block dates again after freeing it up.
        -> date blocked in user schedule. */
        command = createBlockDateCommand(toBlock);
        assertCommandSuccess(command, toBlock);
        model.freeDateForCurrentUser(toBlock);

        /* Case: user logs in normally and attempts to block dates after listing schedule.
        -> date blocked in user schedule. */
        command = createBlockDateCommand(toBlock);
        assertCommandSuccess(command, toBlock);
        model.freeDateForCurrentUser(toBlock);

        /* ------------------------ Perform invalid block operations ----------------------------- */

        /* Case: user logs in normally and attempts to block a date with invalid week -> not blocked. */
        command = createBlockDateCommand(INVALID_WEEK, VALID_DAY, VALID_TIME);
        assertCommandFailure(command, Week.MESSAGE_WEEK_CONSTRAINTS);

        /* Case: user logs in normally and attempts to block a date with invalid day -> not blocked. */
        command = createBlockDateCommand(VALID_WEEK, INVALID_DAY, VALID_TIME);
        assertCommandFailure(command, Day.MESSAGE_DAY_CONSTRAINTS);

        /* Case: user logs in normally and attempts to block a date with invalid time -> not blocked. */
        command = createBlockDateCommand(VALID_WEEK, VALID_DAY, INVALID_TIME);
        assertCommandFailure(command, Time.MESSAGE_TIME_CONSTRAINTS);

        /* Case: user logs in normally and attempts to block an already blocked date -> not blocked. */
        toBlock = VALID_DATE_READING;
        command = createBlockDateCommand(toBlock);
        assertCommandSuccess(command, toBlock);
        assertCommandFailure(command, BlockDateCommand.MESSAGE_DUPLICATE_DATE);
        model.freeDateForCurrentUser(toBlock);

        /* ------------------------ Perform block operations without logging in. ----------------------------- */

        model.logoutUser();
        /* Case: add a valid date, user not logged in -> date blocked in user schedule. */
        assertCommandFailure(command, String.format(MESSAGE_USER_NOT_LOGGED_IN_FOR_COMMAND,
                BlockDateCommand.COMMAND_WORD));

        /* Case: add a invalid date, user not logged in -> date blocked in user schedule. */
        command = createBlockDateCommand(INVALID_WEEK, INVALID_TIME, INVALID_DAY);
        assertCommandFailure(command, String.format(MESSAGE_USER_NOT_LOGGED_IN_FOR_COMMAND,
                BlockDateCommand.COMMAND_WORD));

    }

    /**
     * Executes the {@code BlockDateCommand} that blocks {@code toAdd} in the currently logged in user's schedule and
     * asserts that the <br>
     * 1. Command box displays an empty string.<br>
     * 2. Command box has the default style class.<br>
     * 3. Result display box displays the  success message of executing {@code BlockDateCommand} with the details of
     * {@code toBlock}.<br>
     * 4. {@code Storage} is added with {@code toBlock}.<br>
     * Verifications  1 and 3 are performed by
     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(String command, Date toBlock) {
        Model expectedModel = getModel();

        expectedModel.addUser(VALID_USER);
        expectedModel.loginUser(VALID_USER);
        expectedModel.blockDateForCurrentUser(toBlock);
        String expectedResultMessage = String.format(BlockDateCommand.MESSAGE_SUCCESS, toBlock);

        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchangedExceptSyncStatus();

    }

    /**
     * Executes {@code command} and asserts that the<br>
     * 1. Command box displays {@code command},<br>
     * 2. Command box has the error style class.<br>
     * 3. Result display box displays {@code expectedResultMEssage}.<br>
     * 4. {@code Storage} and {@code RestaurantListPanel} remain unchanged.<br>
     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertCommandBoxShowsErrorStyle();
    }

    /**
     * Creates a full valid block date command that the user will enter.
     * @return the block date command string.
     */
    private String createBlockDateCommand(Date toBlock) {
        String command = createBlockDateCommand(toBlock.getWeek().toString(),
                toBlock.getDay().toString(),
                toBlock.getTime().toString());
        return command;
    }

    String createBlockDateCommand(String week, String day, String time) {
        String command = BlockDateCommand.COMMAND_WORD + " " + PREFIX_WEEK + week + " "
                + PREFIX_DAY + day + " " + PREFIX_TIME + time;
        return command;
    }

}
