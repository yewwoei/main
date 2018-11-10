package systemtests.jio;

import org.junit.Test;
import seedu.address.logic.commands.jio.CreateJioCommand;
import seedu.address.model.Model;
import seedu.address.model.Name;
import seedu.address.model.jio.Jio;
import seedu.address.model.restaurant.Restaurant;
import systemtests.AddCommandSystemTest;
import systemtests.AddressBookSystemTest;

import static seedu.address.model.jio.JioTestUtil.ADDRESS_DESC_MALA;
import static seedu.address.model.jio.JioTestUtil.MALA;
import static seedu.address.model.jio.JioTestUtil.DAY_DESC_MALA;
import static seedu.address.model.jio.JioTestUtil.NAME_DESC_MALA;
import static seedu.address.model.jio.JioTestUtil.TIME_DESC_MALA;
import static seedu.address.model.jio.JioTestUtil.WEEK_DESC_MALA;

public class CreateJioCommandSystemTest extends AddressBookSystemTest {

    @Test
    public void add() {
        Model model = getModel();

        login();

        /* ------------------------ Perform add operations on the shown unfiltered list ----------------------------- */

        /* Case: add a restaurant without tags to a non-empty address book, command with
         * leading spaces and trailing spaces
         * -> added
         */
        Jio toAdd = MALA;
        String command = "   " + CreateJioCommand.COMMAND_WORD + "  " + NAME_DESC_MALA + "  " + WEEK_DESC_MALA + " "
                + DAY_DESC_MALA + "   " + TIME_DESC_MALA + "   " + ADDRESS_DESC_MALA + " ";
        assertCommandSuccess(command, toAdd);

        /* Case: add a restaurant with all fields same as another restaurant in the address book except name -> added */
        toAdd = new Jio(new Name("notmala"), MALA.getDate(), MALA.getAddress());
        command = CreateJioCommand.COMMAND_WORD + "  " + "n/notmala" + "  " + WEEK_DESC_MALA + " "
                + DAY_DESC_MALA + "   " + TIME_DESC_MALA + "   " + ADDRESS_DESC_MALA + " ";
        assertCommandSuccess(command, toAdd);

        /* Case: add to empty address book -> added */
        //deleteAllRestaurants();
        //assertCommandSuccess(RESTAURANT_A);

        /* Case: add a restaurant with tags, command with parameters in random order -> added */
        toAdd = MALA;
        command = CreateJioCommand.COMMAND_WORD + "  " + TIME_DESC_MALA + "   " + WEEK_DESC_MALA + " "
                + DAY_DESC_MALA + "   " + NAME_DESC_MALA + "  " + ADDRESS_DESC_MALA + " ";
        assertCommandSuccess(command, toAdd);

    }

    private void login() {
        executeCommand("signup u/testuser pwd/password n/testuser p/12345678 e/user@gmail.com");
        executeCommand("login u/testuser pwd/password");
        executeCommand("deleteJio n/mala");
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
        Model expectedModel = getModel();
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
