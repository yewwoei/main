package seedu.address.logic.commands.timetable;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDates.DATE_A;
import static seedu.address.testutil.TypicalDates.DATE_B;
import static seedu.address.testutil.TypicalRestaurants.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserData;
import seedu.address.model.UserPrefs;
import seedu.address.model.timetable.Date;
import seedu.address.testutil.UserBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand, and RedoCommand) and unit tests for
 * {@code FreeDateCommand}
 */
public class FreeDateCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validDate_success() {
        Date dateTofree = DATE_A;
        FreeDateCommand FreeDateCommand = new FreeDateCommand(dateTofree);

        String expectedMessage = String.format(FreeDateCommand.MESSAGE_SUCCESS, dateTofree);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new UserData(),
                new UserBuilder().build());

        expectedModel.freeDateForCurrentUser(dateTofree);
        expectedModel.commitAddressBook();

        assertCommandSuccess(FreeDateCommand, model, commandHistory, expectedMessage, expectedModel);
        model.undoAddressBook();
    }

    @Test
    public void execute_withoutLogin_throwsNotLoggedInCommandException() {
        Date dateTofree = DATE_A;
        ModelManager notLoggedInModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new UserData());

        FreeDateCommand FreeDateCommand = new FreeDateCommand(dateTofree);
        assertCommandFailure(FreeDateCommand, notLoggedInModel, commandHistory,
                String.format(Messages.MESSAGE_USER_NOT_LOGGED_IN_FOR_COMMAND, FreeDateCommand.COMMAND_WORD));
    }

    @Test
    public void execute_alreadyFreeDate_throwsCommandException() {
        Date dateTofree = DATE_A;
        model.freeDateForCurrentUser(dateTofree);

        FreeDateCommand FreeDateCommand = new FreeDateCommand(dateTofree);

        assertCommandFailure(FreeDateCommand, model, commandHistory,
                FreeDateCommand.MESSAGE_ALREADY_FREE);
    }

    @Test
    public void equals() {
        FreeDateCommand freeFirstCommand = new FreeDateCommand(DATE_A);
        FreeDateCommand freeSecondCommand = new FreeDateCommand(DATE_B);

        // same object --> returns true
        assertTrue(freeFirstCommand.equals(freeFirstCommand));

        // same values --> returns true
        FreeDateCommand freeFirstCommandCopy = new FreeDateCommand(DATE_A);
        assertTrue(freeFirstCommand.equals(freeFirstCommandCopy));

        // different types --> returns false
        assertFalse(freeFirstCommand.equals(1));
        assertFalse(freeFirstCommand.equals("random string"));

        // null --> returns false
        assertFalse(freeFirstCommand.equals(null));

        // different dates --> returns false.
        assertFalse(freeFirstCommand.equals(freeSecondCommand));
    }
}
