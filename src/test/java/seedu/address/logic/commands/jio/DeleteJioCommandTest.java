package seedu.address.logic.commands.jio;

import org.junit.jupiter.api.Test;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserData;
import seedu.address.model.UserPrefs;
import seedu.address.model.jio.Jio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.jio.JioTestUtil.DINNER;
import static seedu.address.model.jio.JioTestUtil.LUNCH;
import static seedu.address.model.jio.JioTestUtil.LUNCH_COPY;
import static seedu.address.testutil.TypicalRestaurants.getTypicalAddressBook;

class DeleteJioCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validJio_success() {
        model.createJio(LUNCH);
        Jio jioToDelete = model.getJioList().get(0);
        DeleteJioCommand deleteJioCommand = new DeleteJioCommand(jioToDelete.getName());

        String expectedMessage = String.format(DeleteJioCommand.MESSAGE_SUCCESS, jioToDelete.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new UserData());

        assertCommandSuccess(deleteJioCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidJio_throwsCommandException() {
        DeleteJioCommand deleteCommand = new DeleteJioCommand(DINNER.getName());

        assertCommandFailure(deleteCommand, model, commandHistory, DeleteJioCommand.MESSAGE_NONEXISTENT_JIO);
    }

    @Test
    public void equals() {
        DeleteJioCommand deleteLunchCommand = new DeleteJioCommand(LUNCH.getName());
        DeleteJioCommand deleteDinnerCommand = new DeleteJioCommand(DINNER.getName());

        // same object -> returns true
        assertTrue(deleteLunchCommand.equals(deleteLunchCommand));

        // same values -> returns true
        DeleteJioCommand deleteLunchCommandCopy = new DeleteJioCommand(LUNCH_COPY.getName());
        assertTrue(deleteLunchCommand.equals(deleteLunchCommandCopy));

        // different types -> returns false
        assertFalse(deleteLunchCommand.equals(1));

        // null -> returns false
        assertFalse(deleteLunchCommand.equals(null));

        // different restaurant -> returns false
        assertFalse(deleteLunchCommand.equals(deleteDinnerCommand));
    }
}