package seedu.address.model.accounting;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.UUID;

import seedu.address.model.user.User;

public class Debt {

    private User debtor;
    private User creditor;
    private double amount;
    private String debtID;
    private DebtStatus status;

    public Debt( User debtor, User creditor, double amount){
        requireAllNonNull( debtor, creditor, amount);
        this.debtor = debtor;
        this.creditor = creditor;
        this.amount = amount;
        this.debtID = UUID.randomUUID().toString();
        this.status = DebtStatus.PENDING;
    }

    public String getDebtStatus(){ return this.status.toString(); }

    public String getDebtor(){ return this.debtor.getUsername().toString(); }

    public String getCreditor(){ return this.creditor.getUsername().toString(); }

    public double getAmount(){ return this.amount; }

    public String getDebtID(){ return debtID; }

    public void changeDebtStatus(){ this.status = DebtStatus.ACCEPTED; }

    public boolean equals(Object other){

        if( other == this ) {
            return true;
        }

        if ( !(other instanceof Debt) ) {
            return false;
        }

        Debt test = (Debt) other;
        return test != null &&
                test.getDebtID().equals(this.getDebtID());
    }

    public String toString(){
        final StringBuilder builder = new StringBuilder();
        builder.append(" Creditor: ")
                .append(this.getCreditor())
                .append(" Debtor: ")
                .append(this.getDebtor())
                .append(" Amount: ")
                .append(this.getAmount())
                .append(" Status ")
                .append(this.getDebtStatus())
                .append(" ID: ")
                .append(this.getDebtID());
        return builder.toString();
    }

}
