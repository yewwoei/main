package seedu.address.logic.commands.jio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.model.jio.JioTestUtil.DINNER;
import static seedu.address.model.jio.JioTestUtil.JANE;
import static seedu.address.model.jio.JioTestUtil.LUNCH;
import static seedu.address.testutil.TypicalRestaurants.getTypicalAddressBook;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserData;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.UserBuilder;

public class JoinJioCommandTest {
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData(),
            new UserBuilder().build());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullJioName_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new JoinJioCommand(null);
    }

    @Test
    public void execute_nonexistentJio_failure() throws Exception {
        thrown.expect(CommandException.class);
        JoinJioCommand joinJioCommand = new JoinJioCommand(LUNCH.getName());
        joinJioCommand.execute(model, commandHistory);
    }

    @Test
    public void execute_alreadyJoinedJio_failure() throws Exception {
        thrown.expect(CommandException.class);
        thrown.expectMessage("You have already joined this jio");
        model.createJio(LUNCH);
        assertTrue(LUNCH.hasUser(new UserBuilder().build()));

        JoinJioCommand joinJioCommand = new JoinJioCommand(LUNCH.getName());
        joinJioCommand.execute(model, commandHistory);
    }

    @Test
    public void execute() throws Exception {
        model.createJio(LUNCH);
        assertFalse(LUNCH.hasUser(JANE));
        model.logoutUser();

        model.addUser(JANE);
        model.loginUser(JANE);
        JoinJioCommand joinJioCommand = new JoinJioCommand(LUNCH.getName());
        joinJioCommand.execute(model, commandHistory);

        assertTrue(model.getJioList().stream().anyMatch(jio -> (jio.equals(LUNCH) && jio.hasUser(JANE))));
    }

    @Test
    public void equals() {
        JoinJioCommand joinLunchCommand = new JoinJioCommand(LUNCH.getName());
        JoinJioCommand joinDinnerCommand = new JoinJioCommand(DINNER.getName());

        // same object -> returns true
        assertTrue(joinLunchCommand.equals(joinLunchCommand));

        // same values -> returns true
        JoinJioCommand addLunchCommandCopy = new JoinJioCommand(LUNCH.getName());
        assertTrue(joinLunchCommand.equals(addLunchCommandCopy));

        // different types -> returns false
        assertFalse(joinLunchCommand.equals(1));

        // null -> returns false
        assertFalse(joinLunchCommand.equals(null));

        // different restaurant -> returns false
        assertFalse(joinLunchCommand.equals(joinDinnerCommand));
    }
}
