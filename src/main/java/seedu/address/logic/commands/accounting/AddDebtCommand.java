package seedu.address.logic.commands.accounting;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.accounting.Debt;

/**
 * Adds a debt to a user.
 */
public class AddDebtCommand extends Command {
    public static final String COMMAND_WORD = "addDebt";

    // TODO
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a request to debtor and add to record. "
            + "Parameters: "
            + PREFIX_NAME + "DEBTOR"
            + PREFIX_AMOUNT + "AMOUNT"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Kate Ng"
            + PREFIX_AMOUNT + "6.5";

    // TODO
    public static final String MESSAGE_SUCCESS = "Debt Request sent:: %1$s";

    // TODO
    private final Debt toAdd;

    /**
     * Creates an AddDebtCommand to add the specified {@code Debt}model.addRestaurant(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
     */
    public AddDebtCommand(Debt toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        //requireNonNull(model);
        //model.addDebt(toAdd);
        //model.commitAddressBook();
        //return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.toString()));
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDebtCommand // instanceof handles nulls
                && toAdd.equals(((AddDebtCommand) other).toAdd));
    }
}
