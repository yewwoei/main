package seedu.address.logic.commands.accounting;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.Before;
import org.junit.Test;

import seedu.address.commons.core.Messages;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserData;
import seedu.address.model.UserPrefs;
import seedu.address.model.accounting.Amount;
import seedu.address.model.user.Username;
import seedu.address.testutil.TypicalUsers;

public class AddDebtCommandIntegrationTest {

    private Model model;

    private CommandHistory commandHistory = new CommandHistory();

    private Username validUserA = TypicalUsers.getTypicalUsers().get(0).getUsername();

    private Username invalidUser = new Username("NOTAUSER");

    private Username currentUserName = TypicalUsers.getTypicalUsers().get(1).getUsername();

    private Amount validAmountA = new Amount("17");

    private Amount invalidAmountA = new Amount("0");

    private Amount invalidAmountB = new Amount("999999999");

    @Before
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs(),
                TypicalUsers.getTypicalUserData(), TypicalUsers.getTypicalUsers().get(1));
    }

    @Test
    public void execute_newDebt_success() throws CommandException {
        UserData data = new UserData(TypicalUsers.getTypicalUserData());

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                TypicalUsers.getTypicalUserData(), TypicalUsers.getTypicalUsers().get(1));
        System.out.println(expectedModel.getDebtList().toString());
        System.out.println(model.getDebtList().toString());

        expectedModel.addDebt(validUserA, validAmountA);

        System.out.println(expectedModel.getDebtList().toString());
        System.out.println(model.getDebtList().toString());

        new AddDebtCommand(validUserA, validAmountA).execute(model, commandHistory);
        assertCommandSuccess(new AddDebtCommand(validUserA, validAmountA), model, commandHistory,
                String.format(AddDebtCommand.MESSAGE_SUCCESS, validUserA, validAmountA.toDouble()), expectedModel);
    }

    @Test
    public void execute_notLoggedIn() {
        Model modelNotLoggedIn = new ModelManager(new AddressBook(), new UserPrefs(),
                TypicalUsers.getTypicalUserData());
        assertCommandFailure(new AddDebtCommand(validUserA, validAmountA), modelNotLoggedIn, commandHistory,
                String.format(Messages.MESSAGE_USER_NOT_LOGGED_IN_FOR_COMMAND, AddDebtCommand.COMMAND_WORD));
    }

    @Test
    public void execute_userNotInModel() {
        assertCommandFailure(new AddDebtCommand(invalidUser, validAmountA), model, commandHistory,
                AddDebtCommand.MESSAGE_NO_SUCH_USER);
    }

    @Test
    public void execute_isSameUser() {
        assertCommandFailure(new AddDebtCommand(currentUserName, validAmountA), model, commandHistory,
                AddDebtCommand.MESSAGE_CANNOT_ADD_DEBT_TO_ONESELF);
    }

    @Test
    public void execute_amountZero_throwsCommandException() {
        assertCommandFailure(new AddDebtCommand(validUserA, invalidAmountA), model, commandHistory,
                AddDebtCommand.MESSAGE_INVALID_AMOUNT);
    }

    @Test
    public void execute_amountTooLarge_throwsCommandException() {
        assertCommandFailure(new AddDebtCommand(validUserA, invalidAmountB), model, commandHistory,
                AddDebtCommand.MESSAGE_INVALID_AMOUNT);
    }

}
