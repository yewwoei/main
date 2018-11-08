package seedu.address.logic.commands.jio;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.jio.JioTestUtil.DINNER;
import static seedu.address.model.jio.JioTestUtil.LUNCH;
import static seedu.address.testutil.TypicalRestaurants.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserData;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.UserBuilder;

public class ListJioCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        UserData userData = new UserData();
        userData.addJio(LUNCH);
        userData.addJio(DINNER);
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), userData, new UserBuilder().build());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), userData, new UserBuilder().build());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListJioCommand(), model, commandHistory, ListJioCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        expectedModel.updateFilteredJioList(jio -> true);
        assertCommandSuccess(new ListJioCommand(), model, commandHistory, ListJioCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute() {
    }
}
