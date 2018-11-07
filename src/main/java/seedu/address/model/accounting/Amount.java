package seedu.address.model.accounting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Class representing the debt amount.
 */
public class Amount {

    public static final String MESSAGE_AMOUNT_CONSTRAINTS =
            "Amount should only contain numbers up to 2 decimal places.";
    public static final String AMOUNT_VALIDATION_REGEX = "\\d+(\\.\\d{1,2})?";

    private final double amount;

    public Amount(String amt) {
        requireNonNull(amt);
        checkArgument(isValidAmount(amt), MESSAGE_AMOUNT_CONSTRAINTS);
        amount = Double.valueOf(amt).doubleValue();
    }

    public double toDouble() {
        return amount;
    }

    public String toString() {
        return String.valueOf(amount);
    }

    public static boolean isValidAmount(String test) {
        return test.matches(AMOUNT_VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Amount
                && amount == ((Amount) other).amount);

    }

}
