package seedu.address.model.accounting;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class representing the debt ID.
 */
public class DebtId {

    private String id;

    public DebtId() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(dNow);
        this.id = datetime;
    }

    public String toString(DebtId debtId) {
        return this.id;
    }
}
