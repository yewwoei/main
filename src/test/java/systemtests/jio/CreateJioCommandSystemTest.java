package systemtests.jio;

import org.junit.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.jio.CreateJioCommand;
import seedu.address.model.Model;
import seedu.address.model.Name;
import seedu.address.model.jio.Jio;
import seedu.address.model.restaurant.Restaurant;
import seedu.address.model.user.User;
import seedu.address.testutil.TypicalUsers;
import systemtests.AddCommandSystemTest;
import systemtests.AddressBookSystemTest;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.jio.JioTestUtil.ADDRESS_DESC_MALA;
import static seedu.address.model.jio.JioTestUtil.MALA;
import static seedu.address.model.jio.JioTestUtil.DAY_DESC_MALA;
import static seedu.address.model.jio.JioTestUtil.GROUP_DESC_MALA;
import static seedu.address.model.jio.JioTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.model.jio.JioTestUtil.INVALID_DAY_DESC;
import static seedu.address.model.jio.JioTestUtil.INVALID_NAME_DESC;
import static seedu.address.model.jio.JioTestUtil.INVALID_TIME_DESC;
import static seedu.address.model.jio.JioTestUtil.INVALID_WEEK_DESC;
import static seedu.address.model.jio.JioTestUtil.NAME_DESC_MALA;
import static seedu.address.model.jio.JioTestUtil.TIME_DESC_MALA;
import static seedu.address.model.jio.JioTestUtil.WEEK_DESC_MALA;

public class CreateJioCommandSystemTest extends AddressBookSystemTest {

    private Model expectedModel;

    @Test
    public void add() {
        User currentUser = TypicalUsers.getTypicalUsers().get(0);
        addUser(currentUser);
        login(currentUser);
        clearJio();

        expectedModel = getModel();
        expectedModel.addUser(currentUser);
        expectedModel.loginUser(currentUser);
        expectedModel.clearJio();

        /* ------------------------ Perform add operations on the shown unfiltered list ----------------------------- */

        /* Case: add a jio to user data, command with
         * leading spaces and trailing spaces
         * -> added
         */
        Jio toAdd = MALA;
        String command = "   " + CreateJioCommand.COMMAND_WORD + "  " + NAME_DESC_MALA + "  " + WEEK_DESC_MALA + " "
                + DAY_DESC_MALA + "   " + TIME_DESC_MALA + "   " + ADDRESS_DESC_MALA + " ";
        assertCommandSuccess(command, toAdd);

        /* Case: add a jio with all fields same as another jio in the address book except name -> added */
        toAdd = new Jio(new Name("notmala"), MALA.getDate(), MALA.getAddress());
        command = CreateJioCommand.COMMAND_WORD + "  " + "n/notmala" + "  " + WEEK_DESC_MALA + " "
                + DAY_DESC_MALA + "   " + TIME_DESC_MALA + "   " + ADDRESS_DESC_MALA + " ";
        assertCommandSuccess(command, toAdd);

        /* Case: add a jio, command with parameters in random order -> added */
        toAdd = new Jio(new Name("thirdjio"), MALA.getDate(), MALA.getAddress());
        command = CreateJioCommand.COMMAND_WORD + "  " + TIME_DESC_MALA + "   " + WEEK_DESC_MALA + " "
                + DAY_DESC_MALA + "   " + "n/thirdjio" + "  " + ADDRESS_DESC_MALA + " ";
        assertCommandSuccess(command, toAdd);

        /* Case: add a jio, specified as group jio -> added */
        /*
        toAdd = new Jio(new Name("fourthjio"), MALA.getDate(), MALA.getAddress());
        command = "   " + CreateJioCommand.COMMAND_WORD + "  " + "n/fourthjio" + "  " + WEEK_DESC_MALA + " "
                + DAY_DESC_MALA + "   " + TIME_DESC_MALA + "   " + ADDRESS_DESC_MALA + " " + GROUP_DESC_MALA + " ";
        assertCommandSuccess(command, toAdd);
        */


        /* ------------ Perform add operation while a jio card is selected --------------- */

        /* Case: selects first card in the jio list, add a jio
        -> added, card selection remains unchanged */
        listJio();
        selectitem(Index.fromOneBased(1));
        toAdd = new Jio(new Name("fifthjio"), MALA.getDate(), MALA.getAddress());
        command = "   " + CreateJioCommand.COMMAND_WORD + "  " + "n/fifthjio" + "  " + WEEK_DESC_MALA + " "
                + DAY_DESC_MALA + "   " + TIME_DESC_MALA + "   " + ADDRESS_DESC_MALA + " ";
        assertCommandSuccess(command, toAdd);


        /* ------------------- Perform invalid createJio operations ----------------------- */

        /* Case: add a duplicate jio -> rejected */
        command = "   " + CreateJioCommand.COMMAND_WORD + "  " + NAME_DESC_MALA + "  " + WEEK_DESC_MALA + " "
                + DAY_DESC_MALA + "   " + TIME_DESC_MALA + "   " + ADDRESS_DESC_MALA + " ";
        assertCommandFailure(command, CreateJioCommand.MESSAGE_DUPLICATE_JIO);

        /* Case: add a duplicate jio except with different address -> rejected */
        command = "   " + CreateJioCommand.COMMAND_WORD + "  " + NAME_DESC_MALA + "  " + WEEK_DESC_MALA + " "
                + DAY_DESC_MALA + "   " + TIME_DESC_MALA + "   " + "a/notmala" + " ";
        assertCommandFailure(command, CreateJioCommand.MESSAGE_DUPLICATE_JIO);

        /* Case: missing name -> rejected */
        command = "   " + CreateJioCommand.COMMAND_WORD + "  " + WEEK_DESC_MALA + " "
                + DAY_DESC_MALA + "   " + TIME_DESC_MALA + "   " + "a/notmala" + " ";
        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateJioCommand.MESSAGE_USAGE));

        /* Case: missing week -> rejected */
        command = "   " + CreateJioCommand.COMMAND_WORD + "  " + NAME_DESC_MALA + "  "
                + DAY_DESC_MALA + "   " + TIME_DESC_MALA + "   " + ADDRESS_DESC_MALA + " ";
        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateJioCommand.MESSAGE_USAGE));

        /* Case: missing day -> rejected */
        command = "   " + CreateJioCommand.COMMAND_WORD + "  " + NAME_DESC_MALA + "  "
                + WEEK_DESC_MALA + "   " + TIME_DESC_MALA + "   " + ADDRESS_DESC_MALA + " ";
        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateJioCommand.MESSAGE_USAGE));

        /* Case: missing time -> rejected */
        command = "   " + CreateJioCommand.COMMAND_WORD + "  " + NAME_DESC_MALA + "  "
                + WEEK_DESC_MALA + "   " + DAY_DESC_MALA + "   " + ADDRESS_DESC_MALA + " ";
        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateJioCommand.MESSAGE_USAGE));

        /* Case: missing address -> rejected */
        command = "   " + CreateJioCommand.COMMAND_WORD + "  " + NAME_DESC_MALA + "  "
                + WEEK_DESC_MALA + "   " + DAY_DESC_MALA + "   " + TIME_DESC_MALA + " ";
        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateJioCommand.MESSAGE_USAGE));

        /* Case: invalid keyword -> rejected */
        command = "   " + "creatingjio" + "  " + NAME_DESC_MALA + "  "
                + WEEK_DESC_MALA + "   " + DAY_DESC_MALA + "   " + TIME_DESC_MALA + " ";
        assertCommandFailure(command, Messages.MESSAGE_UNKNOWN_COMMAND);

        /* Case: invalid name -> rejected */
        command = "   " + CreateJioCommand.COMMAND_WORD + "  " + INVALID_NAME_DESC + "  " + WEEK_DESC_MALA + " "
                + DAY_DESC_MALA + "   " + TIME_DESC_MALA + "   " + ADDRESS_DESC_MALA + " ";
        assertCommandFailure(command, Name.MESSAGE_NAME_CONSTRAINTS);

        /* Case: invalid week -> rejected */
        command = "   " + CreateJioCommand.COMMAND_WORD + "  " + NAME_DESC_MALA + "  " + INVALID_WEEK_DESC + " "
                + DAY_DESC_MALA + "   " + TIME_DESC_MALA + "   " + ADDRESS_DESC_MALA + " ";
        assertCommandFailure(command, Name.MESSAGE_NAME_CONSTRAINTS);

        /* Case: invalid day -> rejected */
        command = "   " + CreateJioCommand.COMMAND_WORD + "  " + NAME_DESC_MALA + "  " + WEEK_DESC_MALA + " "
                + INVALID_DAY_DESC + "   " + TIME_DESC_MALA + "   " + ADDRESS_DESC_MALA + " ";
        assertCommandFailure(command, Name.MESSAGE_NAME_CONSTRAINTS);

        /* Case: invalid time -> rejected */
        command = "   " + CreateJioCommand.COMMAND_WORD + "  " + NAME_DESC_MALA + "  " + WEEK_DESC_MALA + " "
                + DAY_DESC_MALA + "   " + INVALID_TIME_DESC + "   " + ADDRESS_DESC_MALA + " ";
        assertCommandFailure(command, Name.MESSAGE_NAME_CONSTRAINTS);

        /* Case: invalid address -> rejected */
        command = "   " + CreateJioCommand.COMMAND_WORD + "  " + NAME_DESC_MALA + "  " + WEEK_DESC_MALA + " "
                + DAY_DESC_MALA + "   " + TIME_DESC_MALA + "   " + INVALID_ADDRESS_DESC + " ";
        assertCommandFailure(command, Name.MESSAGE_NAME_CONSTRAINTS);

    }

    /**
     * Executes the {@code AddCommand} that adds {@code toAdd} to the model and asserts that the,<br>
     * 1. Command box displays an empty string.<br>
     * 2. Command box has the default style class.<br>
     * 3. Result display box displays the success message of executing {@code AddCommand} with the details of
     * {@code toAdd}.<br>
     * 4. {@code Storage} and {@code RestaurantListPanel} equal to the corresponding components in
     * the current model added with {@code toAdd}.<br>
     * 5. Browser url and selected card remain unchanged.<br>
     * 6. Status bar's sync status changes.<br>
     * Verifications 1, 3 and 4 are performed by
     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(Jio toAdd) {
        //assertCommandSuccess(RestaurantUtil.getAddCommand(toAdd), toAdd);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(Restaurant)}.
     * Executes {@code command}
     * instead.
     * @see AddCommandSystemTest#assertCommandSuccess(Restaurant)
     */
    private void assertCommandSuccess(String command, Jio toAdd) {
        //Model expectedModel = getModel();
        expectedModel.createJio(toAdd);
        String expectedResultMessage = String.format(CreateJioCommand.MESSAGE_SUCCESS, toAdd);

        assertCommandSuccess(command, expectedModel, expectedResultMessage);
    }

    /**
     * Performs the same verification as
     * {@code assertCommandSuccess(String, Restaurant)} except asserts that the,<br>
     * 1. Result display box displays {@code expectedResultMessage}.<br>
     * 2. {@code Storage} and {@code RestaurantListPanel} equal to the corresponding components in
     * {@code expectedModel}.<br>
     * @see AddCommandSystemTest#assertCommandSuccess(String, Restaurant)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
        executeCommand(command);
        assertApplicationDisplaysExpected("",
                expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsDefaultStyle();
        //assertStatusBarUnchangedExceptSyncStatus();
    }

    /**
     * Executes {@code command} and asserts that the,<br>
     * 1. Command box displays {@code command}.<br>
     * 2. Command box has the error style class.<br>
     * 3. Result display box displays {@code expectedResultMessage}.<br>
     * 4. {@code Storage} and {@code RestaurantListPanel} remain unchanged.<br>
     * 5. Browser url, selected card and status bar remain unchanged.<br>
     * Verifications 1, 3 and 4 are performed by
     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
