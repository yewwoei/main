package seedu.address.logic.commands.friend;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.user.Friendship;

/**
 * Adds a Friend to the user's profile.
 */
public class AddFriendCommand extends Command {
    public static final String COMMAND_WORD = "addFriend";

    // TODO
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sends a friend request to the other User"
            + "Parameters: "
            + PREFIX_NAME + "USERNAME\n"
            + "Example: " + COMMAND_WORD + " "
            +PREFIX_NAME + "Meena567";

    // TODO
    public static final String MESSAGE_SUCCESS = "Friend request sent to: %1$s";

    // TODO
    public static final String MESSAGE_DUPLICATE_FRIEND_REQUEST = "You have already sent friend request to this User";

    // TODO
    private final Friendship toAdd;

    /**
     * Creates a friend request to add the specified {@code Integer} friend.
     */
    public AddFriendCommand(Friendship toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
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
                || (other instanceof AddFriendCommand // instanceof handles nulls
                && toAdd.equals(((AddFriendCommand) other).toAdd));
    }
}
