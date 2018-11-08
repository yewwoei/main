package seedu.address.logic.commands.accounting;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalRestaurants.getTypicalAddressBook;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.accounting.Amount;
import seedu.address.model.accounting.Debt;
import seedu.address.model.accounting.DebtStatus;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;
import seedu.address.testutil.TypicalUsers;
import seedu.address.testutil.UserBuilder;

public class AddDebtCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            TypicalUsers.getTypicalUserData(), new UserBuilder().build());

    private CommandHistory commandHistory = new CommandHistory();

    private Username VALID_USER_A = TypicalUsers.getTypicalUsers().get(0).getUsername();

    private Username VALID_USER_B = TypicalUsers.getTypicalUsers().get(2).getUsername();

    private Username INVALID_USER = new Username("NOTAUSER");

    private Username CURRENT_USER = TypicalUsers.getTypicalUsers().get(1).getUsername();

    private User currentUser = TypicalUsers.getTypicalUsers().get(1);

    private Amount VALID_AMOUNT_A = new Amount("13");

    private Amount VALID_AMOUNT_B = new Amount("101");

    private Amount INVALID_AMOUNT_A = new Amount("0");

    private Amount INVALID_AMOUNT_B = new Amount("999999999");

    final ArrayList<Debt> debtsAdded = new ArrayList<>();

    @Test
    public void constructor_nullDebt_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddDebtCommand(null, null);
    }

    @Test
    public void constructor_nullUser_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddDebtCommand(null, VALID_AMOUNT_A);
    }

    @Test
    public void constructor_nullAmount_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddDebtCommand(VALID_USER_A, null);
    }

    @Test
    public void execute_debtAcceptedByModel_addSuccessful() throws Exception {
        ModelStubforDebt modelStub = new ModelStubforDebt();

        CommandResult commandResult = new AddDebtCommand(VALID_USER_A, VALID_AMOUNT_A).execute(modelStub, commandHistory);

        assertEquals(String.format(AddDebtCommand.MESSAGE_SUCCESS, VALID_USER_A, VALID_AMOUNT_A.toDouble()),
                commandResult.feedbackToUser);

        assertEquals(1, modelStub.debtsAdded.size());
        assertEquals(VALID_USER_A, modelStub.debtsAdded.get(0).getDebtor().getUsername());
        assertEquals(VALID_AMOUNT_A, modelStub.debtsAdded.get(0).getAmount());
        assertEquals(currentUser, modelStub.debtsAdded.get(0).getCreditor());
        assertEquals(DebtStatus.PENDING, modelStub.debtsAdded.get(0).getDebtStatus());

        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_notLoggedIn_throwsCommandException() throws Exception {

        AddDebtCommand addDebtCommand = new AddDebtCommand(VALID_USER_A, VALID_AMOUNT_A);
        ModelStub modelStub = new ModelStubforDebt();
        ((ModelStubforDebt) modelStub).loggedIn = false;

        thrown.expect(CommandException.class);
        thrown.expectMessage(String.format
                (Messages.MESSAGE_USER_NOT_LOGGED_IN_FOR_COMMAND, AddDebtCommand.COMMAND_WORD));
        addDebtCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void execute_userNotInModel_throwsCommandException() throws Exception {
        AddDebtCommand addDebtCommand = new AddDebtCommand(INVALID_USER, VALID_AMOUNT_A);
        ModelStub modelStub = new ModelStubforDebt();

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddDebtCommand.MESSAGE_NO_SUCH_USER);
        addDebtCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void execute_isSameUser_throwsCommandException() throws Exception {
        AddDebtCommand addDebtCommand = new AddDebtCommand(CURRENT_USER, VALID_AMOUNT_A);
        ModelStub modelStub = new ModelStubforDebt();

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddDebtCommand.MESSAGE_CANNOT_ADD_DEBT_TO_ONESELF);
        addDebtCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void execute_amountZero_throwsCommandException() throws Exception {
        AddDebtCommand addDebtCommand = new AddDebtCommand(VALID_USER_A, INVALID_AMOUNT_A);
        ModelStub modelStub = new ModelStubforDebt();

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddDebtCommand.MESSAGE_INVALID_AMOUNT);
        addDebtCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void execute_amountTooLarge_throwsCommandException() throws Exception {
        AddDebtCommand addDebtCommand = new AddDebtCommand(VALID_USER_A, INVALID_AMOUNT_B);
        ModelStub modelStub = new ModelStubforDebt();

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddDebtCommand.MESSAGE_INVALID_AMOUNT);
        addDebtCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        AddDebtCommand test1 = new AddDebtCommand(VALID_USER_A, VALID_AMOUNT_A);
        AddDebtCommand test2 = new AddDebtCommand(VALID_USER_B, VALID_AMOUNT_B);

        assertTrue(test1.equals(test1));

        AddDebtCommand test1_1 = new AddDebtCommand(VALID_USER_A, VALID_AMOUNT_A);
        assertTrue(test1.equals(test1_1));

        assertFalse(test1.equals("test1"));

        assertFalse(test1.equals(null));

        assertFalse(test1.equals(test2));
    }


    /**
     * A Model stub that always accept the debt being added.
     */
    private class ModelStubforDebt extends ModelStub {

        final ArrayList<Debt> debtsAdded = new ArrayList<>();
        boolean loggedIn = true;

        @Override
        public boolean isCurrentlyLoggedIn() {
            return loggedIn;
        }

        @Override
        public boolean hasUser(Username username){
            return TypicalUsers.getTypicalUserData().hasUser(username);
        }

        @Override
        public boolean isSameAsCurrentUser(Username username) {
            return currentUser.equals(TypicalUsers.getTypicalUserData().getUser(username));
        }

        @Override
        public void addDebt(Username debtorUsername, Amount amount) {
            requireNonNull(debtorUsername);
            requireNonNull(amount);
            User debtor = TypicalUsers.getTypicalUserData().getUser(debtorUsername);
            Debt toAdd = new Debt(currentUser, debtor, amount);
            debtsAdded.add(toAdd);
        }

    }

}
