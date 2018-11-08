package seedu.address.model.accounting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Class representing the debt ID.
 */
public class DebtId {

    public static final String MESSAGE_DEBTID_CONSTRAINTS =
            "Debt ID should only contain numbers, and it should be 13-15 digits long";
    public static final String DEBTID_VALIDATION_REGEX = "\\d{13,15}";
    private String id;

    public DebtId() {
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddHHmmssSS");
        String strNow = ft.format(now);
        checkArgument(isValidDebtId(strNow), MESSAGE_DEBTID_CONSTRAINTS);
        this.id = strNow;
    }

    public DebtId(String debtId) {
        requireNonNull(debtId);
        checkArgument(isValidDebtId(debtId), MESSAGE_DEBTID_CONSTRAINTS);
        this.id = debtId;
    }

    @Override
    public String toString() {
        return this.id;
    }

    public static boolean isValidDebtId(String test) {
        return test.matches(DEBTID_VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DebtId
                && id.equals(((DebtId) other).id));
    }
}
