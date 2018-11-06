package seedu.address.logic.commands.group;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.TypicalFriendships.FRIENDSHIP_1;
import static seedu.address.testutil.TypicalRestaurants.getTypicalAddressBook;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.friend.AddFriendCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserData;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalFriendships;
import seedu.address.testutil.UserBuilder;

public class AddFriendCommandTest {
    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData(),
            new UserBuilder().build());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullAddFriend_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddFriendCommand(null);
    }

    @Test
    public void execute_addFriendAcceptedByModel_addSuccessful() throws Exception {
        model.addUser(FRIENDSHIP_1.getMe());
        model.addUser(FRIENDSHIP_1.getFriendUser());
        CommandResult commandResult = new AddFriendCommand(FRIENDSHIP_1.getMyUsername())
                .execute(model, commandHistory);

        assertEquals(String.format(AddFriendCommand.MESSAGE_SUCCESS), commandResult.feedbackToUser);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }
}
