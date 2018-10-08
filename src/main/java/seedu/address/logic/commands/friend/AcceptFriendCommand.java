package seedu.address.logic.commands.friend;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.user.Friendship;

/**
 * Accepts a friend request.
 */
public class AcceptFriendCommand extends Command {
    public static final String COMMAND_WORD = "acceptFriend";

    // TODO
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Allows a user to accept a "
            + "friend request.\n"
            + "Parameters: USERNAME\n"
            + "Example: " + COMMAND_WORD + "Meena567";

    // TODO
    public static final String MESSAGE_SUCCESS = "Successfully accepted friend request: %1$s";

    // TODO
    public static final String MESSAGE_NO_REQUEST = "Sorry, that user is not in your " +
            "friend requests' list.";

    // TODO
    private final Friendship toAdd;

    /**
     * Accepts friend request from the specified {@code Friendship} friend.
     */
    public AcceptFriendCommand(Friendship toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        // TODO
        // throw exceptions here
        requireNonNull(model);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcceptFriendCommand // instanceof handles nulls
                && toAdd.equals(((AcceptFriendCommand) other).toAdd));
    }
}
