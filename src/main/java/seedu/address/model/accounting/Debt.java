package seedu.address.model.accounting;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.UUID;

import seedu.address.model.user.User;

public class Debt {

    private User creditor;
    private User debtor;
    private double amount;
    private String debtID;
    private DebtStatus status;

    public Debt( User creditor, User debtor, double amount ){
        requireAllNonNull( debtor, creditor, amount);
        this.creditor = creditor;
        this.debtor = debtor;
        this.amount = amount;
        this.debtID = UUID.randomUUID().toString();
        this.status = DebtStatus.PENDING;
    }

    public Debt( User creditor, User debtor, double amount, String debtID, DebtStatus status ){
        requireAllNonNull( debtor, creditor, amount);
        this.creditor = creditor;
        this.debtor = debtor;
        this.amount = amount;
        this.debtID = debtID;
        this.status = status;
    }

    public DebtStatus getDebtStatus(){ return this.status; }

    public User getDebtor(){ return this.debtor; }

    public User getCreditor(){ return this.creditor; }

    public double getAmount(){ return this.amount; }

    public String getDebtID(){ return debtID; }

    public String changeDebtStatus(DebtStatus changeTo){
        if( this.getDebtStatus().toString() == "PENDING" && changeTo == DebtStatus.ACCEPTED ){
            this.status = changeTo;
            return "Request Accepted";
        }

        else if( this.getDebtStatus().toString() == "ACCEPTED" && changeTo == DebtStatus.CLEARED){
            this.status = changeTo;
            return "Debt Cleared";
        }
        else{
            return "No a valid action";
        }
    }

    public boolean equals(Object other){

        if( other == this ) {
            return true;
        }

        if ( !(other instanceof Debt) ) {
            return false;
        }

        Debt test = (Debt) other;
        return test != null
                && test.getCreditor().equals(this.getCreditor())
                && test.getDebtID().equals(this.getDebtID())
                && test.getAmount()==this.getAmount()
                && test.getDebtID().equals(this.getDebtID());
    }

    public String toString(){
        final StringBuilder builder = new StringBuilder();
        builder.append(" Creditor: ")
                .append(this.getCreditor().getUsername().toString())
                .append(" Debtor: ")
                .append(this.getDebtor().getUsername().toString())
                .append(" Amount: ")
                .append(String.valueOf(this.getAmount()))
                .append(" Status ")
                .append(this.getDebtStatus().toString())
                .append(" ID: ")
                .append(this.getDebtID());
        return builder.toString();
    }

}
