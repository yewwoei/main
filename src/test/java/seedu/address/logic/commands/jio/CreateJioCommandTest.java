package seedu.address.logic.commands.jio;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserData;
import seedu.address.model.UserPrefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.model.jio.JioTestUtil.DINNER;
import static seedu.address.model.jio.JioTestUtil.LUNCH;
import static seedu.address.testutil.TypicalRestaurants.getTypicalAddressBook;

class CreateJioCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullJio_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new CreateJioCommand(null);
    }

    @Test
    public void execute_jioAcceptedByModel_addSuccessful() throws Exception {
        CommandResult commandResult = new CreateJioCommand(LUNCH).execute(model, commandHistory);

        assertEquals(String.format(CreateJioCommand.MESSAGE_SUCCESS, LUNCH), commandResult.feedbackToUser);
        //assertEquals(Arrays.asList(LUNCH), model.getJioList());
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateJio_throwsCommandException() throws Exception {
        CreateJioCommand createJioCommand = new CreateJioCommand(LUNCH);

        thrown.expect(CommandException.class);
        thrown.expectMessage(CreateJioCommand.MESSAGE_DUPLICATE_JIO);
        createJioCommand.execute(model, commandHistory);
    }

    @Test
    public void equals() {
        CreateJioCommand addLunchCommand = new CreateJioCommand(LUNCH);
        CreateJioCommand addDinnerCommand = new CreateJioCommand(DINNER);

        // same object -> returns true
        assertTrue(addLunchCommand.equals(addLunchCommand));

        // same values -> returns true
        CreateJioCommand addLunchCommandCopy = new CreateJioCommand(LUNCH);
        assertTrue(addLunchCommand.equals(addLunchCommandCopy));

        // different types -> returns false
        assertFalse(addLunchCommand.equals(1));

        // null -> returns false
        assertFalse(addLunchCommand.equals(null));

        // different restaurant -> returns false
        assertFalse(addLunchCommand.equals(addDinnerCommand));
    }


}