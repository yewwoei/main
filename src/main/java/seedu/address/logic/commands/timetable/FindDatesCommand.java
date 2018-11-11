package seedu.address.logic.commands.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.NotLoggedInCommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.timetable.Week;

/**
 * Finds a common time to eat among a list of friends.
 */
public class FindDatesCommand extends Command {
    public static final String COMMAND_WORD = "findDates";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the common free times among friends for the week for a given group."
            + "Parameters: "
            + PREFIX_WEEK + "NUS WEEK"
            + PREFIX_GROUP + "GROUP"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_WEEK + "5"
            + PREFIX_GROUP + "MALA";

    public static final String MESSAGE_SUCCESS = "Listed the common free dates for"
            + " week %1$s below for group %2$s.";

    private final Week weekNumber;
    private final Group group;

    /**
     * Creates a FindDatesCommand to find a common eating timeslot among the {@code Integer} friends.
     */
    public FindDatesCommand(Week weekNumber, Group group) {
        requireAllNonNull(weekNumber, group);
        this.weekNumber = weekNumber;
        this.group = group;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.isCurrentlyLoggedIn()) {
            throw new NotLoggedInCommandException(COMMAND_WORD);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, weekNumber, group));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindDatesCommand // instanceof handles nulls
                && (weekNumber.equals(((FindDatesCommand) other).weekNumber)
                    && group.equals(((FindDatesCommand) other).group)));
    }
}
