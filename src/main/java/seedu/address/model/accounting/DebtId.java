package seedu.address.model.accounting;

import java.util.UUID;

/**
 * Class representing the debt ID.
 */
public class DebtId {

    private String id;

    public DebtId() {
        this.id = UUID.randomUUID().toString();
    }

}
