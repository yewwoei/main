package systemtests.accounting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_USER_NOT_LOGGED_IN_FOR_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.accounting.AddDebtCommand;
import seedu.address.model.Model;
import seedu.address.model.accounting.Amount;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;
import seedu.address.testutil.TypicalUsers;
import systemtests.AddressBookSystemTest;

public class AddDebtCommandSystemTest extends AddressBookSystemTest {

    private  static final String CURRENT_USER = TypicalUsers.getTypicalUsers().get(0).getUsername().toString();
    private static final String VALID_USER_A = TypicalUsers.getTypicalUsers().get(1).getUsername().toString();
    private static final String VALID_USER_B = TypicalUsers.getTypicalUsers().get(2).getUsername().toString();
    private static final String VALID_USER_C = TypicalUsers.getTypicalUsers().get(3).getUsername().toString();
    private static final String INVALID_USER_A = "test a";
    private static final String VALID_AMOUNT = "101";
    private static final String INVALID_AMOUNT_FORMAT = "11.11111111";
    private static final String INVALID_AMOUNT_FORMAT_NEGATIVE = "-17";
    private static final String INVALID_AMOUNT_ZERO = "0";
    private static final String INVALID_AMOUNT_TOO_LAREG = "9999999999999";

    private User currentUser = TypicalUsers.getTypicalUsers().get(0);
    private User otherUserA = TypicalUsers.getTypicalUsers().get(1);
    private User otherUserB = TypicalUsers.getTypicalUsers().get(2);

    private Username userA =  TypicalUsers.getTypicalUsers().get(1).getUsername();
    private Username userB = TypicalUsers.getTypicalUsers().get(2).getUsername();
    private Username userC = TypicalUsers.getTypicalUsers().get(3).getUsername();

    private Amount amount = new Amount("101");
    private Amount amountZero = new Amount("0");
    private Amount amountTooLarge = new Amount("9999999999999");

    @Test
    public void add() {

        addUser(otherUserA);
        addUser(otherUserB);
        addUser(currentUser);
        login(currentUser);
        Model model = getModel();
        model.addUser(otherUserA);
        model.addUser(otherUserB);
        model.addUser(currentUser);
        model.loginUser(currentUser);

        String command = AddDebtCommand.COMMAND_WORD + " " + PREFIX_USERNAME + VALID_USER_A
                        + " " + PREFIX_AMOUNT + VALID_AMOUNT;
        assertCommandSuccess(command, model, userA, amount);
        assertCommandSuccess(command, model, userA, amount);

        command = AddDebtCommand.COMMAND_WORD + " " + PREFIX_USERNAME + VALID_USER_B
                + " " + PREFIX_AMOUNT + VALID_AMOUNT;
        assertCommandSuccess(command, model, userB, amount);
        listJio();

        assertCommandSuccess(command, model, userB, amount);

        selectRestaurant(Index.fromOneBased(3));
        assertCommandSuccess(command, model, userB, amount);

        logout();
        model.logoutUser();
        login(otherUserA);
        model.loginUser(userA);
        assertCommandSuccess(command, model, userB, amount);

        command =  AddDebtCommand.COMMAND_WORD + " " + VALID_USER_B
                + " " + PREFIX_AMOUNT + VALID_AMOUNT;
        assertCommandFailure(command, model, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddDebtCommand.MESSAGE_USAGE));

        command =  AddDebtCommand.COMMAND_WORD + " " + PREFIX_USERNAME + VALID_USER_B
                + " " + VALID_AMOUNT;
        assertCommandFailure(command, model, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddDebtCommand.MESSAGE_USAGE));

        command =  AddDebtCommand.COMMAND_WORD + " " + PREFIX_USERNAME + INVALID_USER_A
                + " " + PREFIX_AMOUNT + VALID_AMOUNT;
        assertCommandFailure(command, model, Username.MESSAGE_USERNAME_CONSTRAINTS);

        command =  AddDebtCommand.COMMAND_WORD + " " + PREFIX_USERNAME + VALID_USER_B
                + " " + PREFIX_AMOUNT + INVALID_AMOUNT_FORMAT;
        assertCommandFailure(command, model, Amount.MESSAGE_AMOUNT_CONSTRAINTS);

        command =  AddDebtCommand.COMMAND_WORD + " " + PREFIX_USERNAME + VALID_USER_B
                + " " + PREFIX_AMOUNT + INVALID_AMOUNT_FORMAT_NEGATIVE;
        assertCommandFailure(command, model, Amount.MESSAGE_AMOUNT_CONSTRAINTS);

        command =  "AddDebt" + " " + PREFIX_USERNAME + VALID_USER_A
                + " " + PREFIX_AMOUNT + VALID_AMOUNT;
        assertCommandFailure(command, model, Messages.MESSAGE_UNKNOWN_COMMAND);

        logout();
        model.logoutUser();
        command =  AddDebtCommand.COMMAND_WORD + " " + PREFIX_USERNAME + VALID_USER_B
                + " " + PREFIX_AMOUNT + VALID_AMOUNT;
        assertCommandFailure(command, model,
                String.format(MESSAGE_USER_NOT_LOGGED_IN_FOR_COMMAND, AddDebtCommand.COMMAND_WORD));

        login(currentUser);
        model.loginUser(currentUser);

        command =  AddDebtCommand.COMMAND_WORD + " " + PREFIX_USERNAME + VALID_USER_C
                + " " + PREFIX_AMOUNT + VALID_AMOUNT;
        assertCommandFailure(command, model, AddDebtCommand.MESSAGE_NO_SUCH_USER);

        command =  AddDebtCommand.COMMAND_WORD + " " + PREFIX_USERNAME + CURRENT_USER
                + " " + PREFIX_AMOUNT + VALID_AMOUNT;
        assertCommandFailure(command, model, AddDebtCommand.MESSAGE_CANNOT_ADD_DEBT_TO_ONESELF);

        command =  AddDebtCommand.COMMAND_WORD + " " + PREFIX_USERNAME + VALID_USER_A
                + " " + PREFIX_AMOUNT + INVALID_AMOUNT_ZERO;
        assertCommandFailure(command, model, AddDebtCommand.MESSAGE_INVALID_AMOUNT);

        command =  AddDebtCommand.COMMAND_WORD + " " + PREFIX_USERNAME + VALID_USER_A
                + " " + PREFIX_AMOUNT + INVALID_AMOUNT_TOO_LAREG;
        assertCommandFailure(command, model, AddDebtCommand.MESSAGE_INVALID_AMOUNT);

    }

    private void assertCommandSuccess(String command, Model expectedModel, Username debtor, Amount amount) {
        expectedModel.addDebt(debtor, amount);
        String expectedResultMessage = String.format(AddDebtCommand.MESSAGE_SUCCESS, debtor, amount.toDouble());
        assertCommandSuccess(command, expectedModel, expectedResultMessage);
    }

    /**
     * Performs the same verification as
     * {@code assertCommandSuccess(String, Model, Username, Amount)} except asserts that the,<br>
     * 1. Result display box displays {@code expectedResultMessage}.<br>
     * 2. {@code Storage} equal to the corresponding components in {@code expectedModel}.<br>
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsDefaultStyle();
    }

    /**
     * Executes {@code command} and asserts that the,<br>
     * 1. Command box displays {@code command}.<br>
     * 2. Command box has the error style class.<br>
     * 3. Result display box displays {@code expectedResultMessage}.<br>
     * 4. {@code Storage} remain unchanged.
     */
    private void assertCommandFailure(String command, Model expectedModel, String expectedResultMessage) {
        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
    }
}
