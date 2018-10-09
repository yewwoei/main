package seedu.address.model.accounting;

import java.util.UUID;

public class DebtId {

    private String id;

    public DebtId() {
        this.id = UUID.randomUUID().toString();
    }

}
