package seedu.address.logic.commands.timetable;

import static org.junit.Assert.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDates.DATE_A;
import static seedu.address.testutil.TypicalRestaurants.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserData;
import seedu.address.model.UserPrefs;
import seedu.address.model.timetable.Date;
import seedu.address.testutil.UserBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand, and RedoCommand) and unit tests for
 * {@code BlockDateCommand}
 */
public class BlockDateCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData(),
            new UserBuilder().build());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validDate_success() {
        Date dateToBlock = DATE_A;
        BlockDateCommand blockDateCommand = new BlockDateCommand(dateToBlock);

        String expectedMessage = String.format(BlockDateCommand.MESSAGE_SUCCESS, dateToBlock);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new UserData(),
                new UserBuilder().build());

        expectedModel.blockDateForCurrentUser(dateToBlock);
        expectedModel.commitAddressBook();

        assertCommandSuccess(blockDateCommand, model, commandHistory, expectedMessage, expectedModel);
        model.undoAddressBook();

    }

    @Test
    public void execute_duplicateDate_throwsCommandException() {
        Date dateToBlock = DATE_A;
        model.blockDateForCurrentUser(dateToBlock);

        BlockDateCommand blockDateCommand = new BlockDateCommand(dateToBlock);

        assertCommandFailure(blockDateCommand, model, commandHistory,
                BlockDateCommand.MESSAGE_DUPLICATE_DATE);
    }


    @Test
    public void execute_withoutLogin_throwsNotLoggedInCommandException() {
        Date dateToBlock = DATE_A;
        ModelManager notLoggedInModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new UserData());

        BlockDateCommand blockDateCommand = new BlockDateCommand(dateToBlock);
        assertCommandFailure(blockDateCommand, notLoggedInModel, commandHistory,
                String.format(Messages.MESSAGE_USER_NOT_LOGGED_IN_FOR_COMMAND, BlockDateCommand.COMMAND_WORD));
    }


    @Test
    public void executeUndoRedo_validDate_sameDateDeleted() throws Exception {
        Date dateToBlock = seedu.address.testutil.TypicalDates.DATE_B;
        BlockDateCommand blockDateCommand = new BlockDateCommand(dateToBlock);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new UserData(),
                new UserBuilder().build());
        expectedModel.blockDateForCurrentUser(dateToBlock);
        expectedModel.commitAddressBook();

        // blocks the date from the current logged in user.
        blockDateCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and frees up the blocked user date.
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        System.out.println(model.hasDateForCurrentUser(dateToBlock));
        assertFalse(model.hasDateForCurrentUser(dateToBlock));

        // redo --> blocks the date again.
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidDate_failure() {

    }

    @Test
    public void equals() {

    }
}
