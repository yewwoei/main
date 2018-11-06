package seedu.address.logic.commands.jio;

import org.junit.Before;
import org.junit.Test;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserData;
import seedu.address.model.UserPrefs;

import static org.junit.Assert.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showRestaurantAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RESTAURANT;
import static seedu.address.testutil.TypicalRestaurants.getTypicalAddressBook;

public class ListJioCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new UserData());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListJioCommand(), model, commandHistory, ListJioCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showRestaurantAtIndex(model, INDEX_FIRST_RESTAURANT);
        assertCommandSuccess(new ListJioCommand(), model, commandHistory, ListJioCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute() {
    }
}