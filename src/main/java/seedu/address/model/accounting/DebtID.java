package seedu.address.model.accounting;

import java.util.UUID;

/**
 * Class representing the debt ID.
 */
public class DebtID {

    private String id;

    public DebtID() {
        this.id = UUID.randomUUID().toString();
    }

}
