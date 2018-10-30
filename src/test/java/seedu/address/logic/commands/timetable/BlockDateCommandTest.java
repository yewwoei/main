package seedu.address.logic.commands.timetable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalRestaurants.getTypicalAddressBook;

import org.junit.Test;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model, UndoCommand, and RedoCommand) and unit tests for
 * {@code BlockDateCommand}
 */
public class BlockDateCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validDate_success() {

    }

    @Test
    public void execute_invalidDate_throwsCommandException() {

    }

    @Test
    public void execute_withoutLogin_throwsNotLoggedInCommandException() {

    }

    @Test
    public void execute_duplicateDate_throwsCommandException() {

    }

    @Test
    public void executeUndoRedo_validDate_success() throws Exception {

    }

    @Test
    public void execute_undoRedo_invalidDate_failure() {

    }

    @Test
    public void equals() {

    }
}
