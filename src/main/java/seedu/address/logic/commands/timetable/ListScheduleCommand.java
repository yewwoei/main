package seedu.address.logic.commands.timetable;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists the available times to eat for the logged in user during a specified week number.
 */
public class ListScheduleCommand extends Command {
    public static final String COMMAND_WORD = "listTime";

    // TODO
    public static final String MESSAGE_USAGE = null;

    // TODO
    public static final String MESSAGE_SUCCESS = null;

    // TODO
    public static final String MESSAGE_DUPLICATE_REVIEW = null;

    // TODO
    private final Integer weekNumber;

    /**
     * Creates a ListScheduleCommand view the free time on the logged in user's timetable.
     */
    public ListScheduleCommand(Integer weekNumber) {
        requireNonNull(weekNumber);
        this.weekNumber = weekNumber;
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
                || (other instanceof ListScheduleCommand // instanceof handles nulls
                && weekNumber.equals(((ListScheduleCommand) other).weekNumber));
    }
}