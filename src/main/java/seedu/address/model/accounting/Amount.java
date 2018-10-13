package seedu.address.model.accounting;

import static java.util.Objects.requireNonNull;

public class Amount {

    private double amount;

    public Amount(double amount) {
        requireNonNull(amount);
        this.amount = amount;
    }

    public double toDouble(Amount amount) {
        return this.amount;
    }
}
