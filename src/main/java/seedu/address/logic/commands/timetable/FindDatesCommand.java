package seedu.address.logic.commands.timetable;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.user.User;

/**
 * Finds a common time to eat among a list of friends.
 */
public class FindDatesCommand extends Command {
    public static final String COMMAND_WORD = "findDates";

    // TODO
    public static final String MESSAGE_USAGE = null;

    // TODO
    public static final String MESSAGE_SUCCESS = null;

    // TODO
    public static final String MESSAGE_DUPLICATE_REVIEW = null;

    // TODO
    private final List<User> friends;

    /**
     * Creates a FindDatesCommand to find a common eating timeslot among the {@code Integer} friends.
     */
    public FindDatesCommand(List<User> friends) {
        requireNonNull(friends);
        this.friends = friends;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        // TODO
        requireNonNull(model);

        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindDatesCommand // instanceof handles nulls
                && friends.equals(((FindDatesCommand) other).friends));
    }
}
