package seedu.address.logic.commands.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * adds a user into a group the logged in user is in.
 */
public class AddGroupCommand extends Command {
    public static final String COMMAND_WORD = "addGroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a group."
            + "Parameters: "
            + PREFIX_GROUP + "GROUP\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP + "My Lonely Club";

    public static final String MESSAGE_SUCCESS = "Group created: %1$s";
    public static final String MESSAGE_NOT_LOGGED_IN = "You must login before adding friends";

    public static final String MESSAGE_DUPLICATE_GROUP = "Sorry, a group with that name exists."
            + " Please choose a different group name.";

    // TODO
    private final Group toAdd;

    /**
     * Creates a group request to add the specified {@code Integer} friend.
     */
    public AddGroupCommand(Group toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.isCurrentlyLoggedIn()) {
            throw new CommandException(MESSAGE_NOT_LOGGED_IN);
        }

        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddGroupCommand // instanceof handles nulls
                && toAdd.equals(((AddGroupCommand) other).toAdd));
    }
}
