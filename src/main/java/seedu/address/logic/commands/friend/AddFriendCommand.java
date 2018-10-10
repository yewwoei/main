package seedu.address.logic.commands.friend;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.user.Friendship;
import seedu.address.model.user.Username;

/**
 * Adds a Friend to the user's profile.
 */
public class AddFriendCommand extends Command {
    public static final String COMMAND_WORD = "addFriend";

    // TODO
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sends a friend request to the other User"
            + "Parameters: "
            + PREFIX_USERNAME + "USERNAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_USERNAME + "Meena567";

    public static final String MESSAGE_SUCCESS = "Friend request sent to: %1$s";

    public static final String MESSAGE_DUPLICATE_FRIEND_REQUEST = "You have already sent friend request to this User";
    public static final String MESSAGE_FRIEND_ALREADY = "You are already friends with this user";

    private final Username toAdd;

    /**
     * Creates a friend request to add the specified {@code Integer} friend.
     */
    public AddFriendCommand(Username toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if(model.hasUsernameFriendRequest(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_FRIEND_REQUEST);
        }

        if(model.hasUsernameFriend(toAdd)) {
            throw new CommandException(MESSAGE_FRIEND_ALREADY);
        }

        model.addFriend(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddFriendCommand // instanceof handles nulls
                && toAdd.equals(((AddFriendCommand) other).toAdd));
    }
}
