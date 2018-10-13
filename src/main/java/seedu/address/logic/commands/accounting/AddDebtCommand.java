package seedu.address.logic.commands.accounting;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.user.Username;

/**
 * Adds a debt to a user.
 */
public class AddDebtCommand extends Command {
    public static final String COMMAND_WORD = "addDebt";

    // TODO
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a request to debtor and add to record. "
            + "Parameters: "
            + PREFIX_USERNAME + "DEBTOR\n"
            + PREFIX_AMOUNT + "AMOUNT\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_USERNAME + "Kate Ng"
            + PREFIX_AMOUNT + "6.5";

    public static final String MESSAGE_SUCCESS = "Debt Request sent: %1$s %2$s";

    private final Username debtor;
    private final double amount;

    /**
     * Creates an AddDebtCommand to add the specified {@code Debt}model.addRestaurant(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
     */
    public AddDebtCommand(Username debtor, double amount) {
        requireNonNull(debtor);
        requireNonNull(amount);
        this.debtor = debtor;
        this.amount = amount;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        model.addDebt(debtor, amount);
        return new CommandResult(String.format(MESSAGE_SUCCESS,debtor,amount));
    }

}
