package seedu.address.logic.commands.exceptions;

/**
 * Represents an error which occurs during an execution of user related {@Link Command}
 * while the user is not logged in.
 */
public class NotLoggedInCommandException extends CommandException{

    public NotLoggedInCommandException() {
        super("You must be logged in to use the command!");
    }

    /**
     * Constructs a new {@code NotLoggedInCommandException} with the specified {@code message} and {@code cause}.
     */
    public NotLoggedInCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
