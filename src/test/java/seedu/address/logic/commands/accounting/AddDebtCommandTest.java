package seedu.address.logic.commands.accounting;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.logic.CommandHistory;

public class AddDebtCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullDebt_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddDebtCommand(null, null);
    }

}
