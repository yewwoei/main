package seedu.address.model.accounting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.accounting.exception.DebtNotFoundException;
import seedu.address.model.accounting.exception.DuplicateDebtException;

/**
 * A list of debts that enforces uniqueness between its elements and does not allow nulls.
 * Supports a minimal set of list operations.
 *
 */


public class UniqueDebtList implements Iterable<Debt> {

    private final ObservableList<Debt> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent debt as the given argument.
     */
    public boolean contains(Debt toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a debt to the list.
     * The debt must not already exist in the list.
     */
    public void add(Debt toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateDebtException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the debt {@code target} in the list with {@code editedDebt}.
     * {@code target} must exist in the list.
     * The debt identity of {@code editedDebt}
     * must not be the same as another existing debt in the list.
     */
    public void setDebt(Debt target, Debt editedDebt) {
        requireAllNonNull(target, editedDebt);

        int index = internalList.indexOf(target);

        if (index == -1) {
            throw new DebtNotFoundException();
        }

        if (!target.equals(editedDebt) && contains(editedDebt)) {
            throw new DuplicateDebtException();
        }

        internalList.set(index, editedDebt);
    }

    /**
     * Removes the equivalent debt from the list.
     * The debt must exist in the list.
     */
    public void remove(Debt toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DebtNotFoundException();
        }
    }

    public void setDebts(UniqueDebtList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code debt}.
     * {@code debts} must not contain duplicate debts.
     */
    public void setDebts(List<Debt> debts) {
        requireAllNonNull(debts);
        if (!debtsAreUnique(debts)) {
            throw new DuplicateDebtException();
        }

        internalList.setAll(debts);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Debt> asUnmodifiableObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

    //@Override
    public Iterator<Debt> iterator() {
            return internalList.iterator();
        }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueDebtList // instanceof handles nulls
                && internalList.equals(((UniqueDebtList) other).internalList));
    }

    @Override
    public int hashCode() {
            return internalList.hashCode();
        }

    /**
    * Returns true if {@code debts} contains only unique debts.
    */
    private boolean debtsAreUnique(List<Debt> debts) {
        for (int i = 0; i < debts.size() - 1; i++) {
            for (int j = i + 1; j < debts.size(); j++) {
                if (debts.get(i).equals(debts.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

