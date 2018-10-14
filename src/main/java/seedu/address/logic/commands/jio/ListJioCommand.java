package seedu.address.logic.commands.jio;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * List the available jios.
 */
public class ListJioCommand extends Command {
    public static final String COMMAND_WORD = "listJio";

    public static final String MESSAGE_SUCCESS = "Listed all restaurants";
    public static final String MESSAGE_NOT_LOGGED_IN = "You must log in to use this command";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        // Check if user is logged in
        if (!model.isCurrentlyLoggedIn()) {
            throw new CommandException(MESSAGE_NOT_LOGGED_IN);
        }

        /*
        String list = "All jios:";
        ObservableList<Jio> allJios = model.getJioList();
        Iterator<Jio> iterator = allJios.iterator();
        while(iterator.hasNext()) {
            list += ("\n" + iterator.next());
        }
        */
        return new CommandResult(MESSAGE_SUCCESS);
    }

}

