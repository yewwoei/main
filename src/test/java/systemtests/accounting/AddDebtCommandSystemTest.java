package systemtests.accounting;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import org.junit.Test;

import seedu.address.logic.commands.accounting.AddDebtCommand;
import seedu.address.model.Model;
import seedu.address.model.accounting.Amount;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;
import seedu.address.testutil.TypicalUsers;
import systemtests.AddressBookSystemTest;

public class AddDebtCommandSystemTest extends AddressBookSystemTest {

    String VALID_USER_A = TypicalUsers.getTypicalUsers().get(1).getUsername().toString();

    String VALID_AMOUNT = "101";

    User currentUser = TypicalUsers.getTypicalUsers().get(0);

    User otherUser = TypicalUsers.getTypicalUsers().get(1);

    Username userA = new Username("benny123");

    Amount amount = new Amount("101");

    @Test
    public void add() {

        addUser(otherUser);
        addUser(currentUser);
        login(currentUser);
        Model model = getModel();
        model.addUser(otherUser);
        model.addUser(currentUser);
        model.loginUser(currentUser);

        String command = AddDebtCommand.COMMAND_WORD + " " + PREFIX_USERNAME + VALID_USER_A
                        + " " + PREFIX_AMOUNT +  VALID_AMOUNT;
        assertCommandSuccess(command, model, userA, amount);
    }

    private void assertCommandSuccess(String command, Model expectedModel, Username debtor, Amount amount) {
        expectedModel.addDebt(debtor, amount);
        String expectedResultMessage = String.format(AddDebtCommand.MESSAGE_SUCCESS, debtor, amount.toDouble());
        assertCommandSuccess(command, expectedModel, expectedResultMessage);
    }

    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsDefaultStyle();
    }

    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
