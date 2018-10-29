package seedu.address.model.accounting.exception;

public class DuplicateDebtException extends RuntimeException {
    public DuplicateDebtException() {
        super("Operation would result in duplicate debts");
    }
}
