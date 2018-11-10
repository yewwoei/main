package seedu.address.logic.commands;

import org.junit.Test;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.user.LogoutCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.user.Username;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.model.util.SampleUserDataUtil;
import seedu.address.testutil.Assert;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

/**
 * Contains tests for Logout Command
 */
public class LogoutCommandTest {

    private Model model = new ModelManager(SampleDataUtil.getSampleAddressBook(), new UserPrefs(),
            SampleUserDataUtil.getSampleUserData());
    private Model expectedModel = new ModelManager(SampleDataUtil.getSampleAddressBook(), new UserPrefs(),
            SampleUserDataUtil.getSampleUserData());
    private CommandHistory commandHistory = new CommandHistory();
    String expectedMessage = LogoutCommand.MESSAGE_SUCCESS;
    Username existingUsername = new Username("navekom");

    @Test
    public void execute_LogoutWithNoCurrentUser() {
        LogoutCommand logoutCommand = new LogoutCommand();
        Assert.assertThrows(CommandException.class, () -> logoutCommand.execute(model, commandHistory));
    }

    @Test
    public void execute_logout() {
        model.loginUser(existingUsername);
        assertCommandSuccess(new LogoutCommand(),
                model, commandHistory, expectedMessage, expectedModel);
    }
}
