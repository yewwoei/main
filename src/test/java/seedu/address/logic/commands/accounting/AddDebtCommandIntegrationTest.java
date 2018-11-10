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
import seedu.address.model.user.User;
import seedu.address.model.user.Username;
import seedu.address.testutil.TypicalUsers;
import seedu.address.testutil.UserBuilder;

public class AddDebtCommandIntegrationTest {

    private Model model;

    private CommandHistory commandHistory = new CommandHistory();

    private Username validUserA = TypicalUsers.getTypicalUsers().get(0).getUsername();

    private Username invalidUser = new Username("NOTAUSER");

    private Username currentUserName = TypicalUsers.getTypicalUsers().get(1).getUsername();

    private User userA = TypicalUsers.getTypicalUsers().get(0);

    private User currentUser = TypicalUsers.getTypicalUsers().get(1);

    private Amount validAmountA = new Amount("17");

    private Amount invalidAmountA = new Amount("0");

    private Amount invalidAmountB = new Amount("999999999");

    @Before
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs(),
                TypicalUsers.getTypicalUserData(), currentUser);
    }

    @Test
    public void execute_newDebt_success() throws CommandException {
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                new UserData(), TypicalUsers.getTypicalUsers().get(1));

        User userACopy = new UserBuilder().withEmail(userA.getEmail().toString())
                .withName(userA.getName().toString())
                .withPassword(userA.getPassword().toString())
                .withPhone(userA.getPhone().toString())
                .withUsername(userA.getUsername().toString())
                .build();
        User currentUserCopy = new UserBuilder().withEmail(currentUser.getEmail().toString())
                .withName(currentUser.getName().toString())
                .withPassword(currentUser.getPassword().toString())
                .withPhone(currentUser.getPhone().toString())
                .withUsername(currentUser.getUsername().toString())
                .build();
        expectedModel.addUser(userACopy);
        expectedModel.addUser(currentUserCopy);
        expectedModel.loginUser(currentUser);

        expectedModel.addDebt(validUserA, validAmountA);
        assertCommandSuccess(new AddDebtCommand(validUserA, validAmountA), model, commandHistory,
                String.format(AddDebtCommand.MESSAGE_SUCCESS, validUserA, validAmountA.toDouble()), expectedModel);
    }

    @Test
    public void execute_notLoggedIn() {
        model.logoutUser();
        assertCommandFailure(new AddDebtCommand(validUserA, validAmountA), model, commandHistory,
                String.format(Messages.MESSAGE_USER_NOT_LOGGED_IN_FOR_COMMAND, AddDebtCommand.COMMAND_WORD));
        model.loginUser(currentUser);
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
