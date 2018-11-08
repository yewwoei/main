package seedu.address.model.accounting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalDebts.DEBT_A;
import static seedu.address.testutil.TypicalDebts.DEBT_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;

import seedu.address.model.accounting.exception.DebtNotFoundException;
import seedu.address.model.accounting.exception.DuplicateDebtException;

public class UniqueDebtListTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final UniqueDebtList debts = new UniqueDebtList();

    @Test
    public void contains_nullDebt_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        debts.contains(null);
    }

    @Test
    public void contains_debtNotInList_returnsFalse() {
        assertFalse(debts.contains(DEBT_A));
    }

    @Test
    public void contains_debtInList_returnsTrue() {
        debts.add(DEBT_A);
        assertTrue(debts.contains(DEBT_A));
    }

    @Test
    public void add_nullDebt_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        debts.add(null);
    }

    @Test
    public void add_duplicateDebt_throwsDuplicateDebtException() {
        debts.add(DEBT_A);
        thrown.expect(DuplicateDebtException.class);
        debts.add(DEBT_A);
    }

    @Test
    public void setDebt_nullTargetDebt_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        debts.setDebt(null, DEBT_A);
    }

    @Test
    public void setDebt_nullEditedDebt_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        debts.setDebt(DEBT_A, null);
    }

    @Test
    public void setDebt_targetDebtNotInList_throwsDebtNotFoundException() {
        thrown.expect(DebtNotFoundException.class);
        debts.setDebt(DEBT_A, DEBT_A);
    }

    @Test
    public void setDebt_editedDebtIsSameDebt_success() {
        debts.add(DEBT_A);
        debts.setDebt(DEBT_A, DEBT_A);
        UniqueDebtList expectedUniqueDebtList = new UniqueDebtList();
        expectedUniqueDebtList.add(DEBT_A);
        assertEquals(expectedUniqueDebtList, debts);
    }

    @Test
    public void setRestaurant_editedDebtHasDifferentIdentity_success() {
        debts.add(DEBT_A);
        debts.setDebt(DEBT_A, DEBT_B);
        UniqueDebtList expectedUniqueDebtList = new UniqueDebtList();
        expectedUniqueDebtList.add(DEBT_B);
        assertEquals(expectedUniqueDebtList, debts);
    }

    @Test
    public void setDebt_editedDebtHasNonUniqueIdentity_throwsDuplicateDebtException() {
        debts.add(DEBT_A);
        debts.add(DEBT_B);
        thrown.expect(DuplicateDebtException.class);
        debts.setDebt(DEBT_A, DEBT_B);
    }

    @Test
    public void remove_nullDebt_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        debts.remove(null);
    }

    @Test
    public void remove_debtDoesNotExist_throwsDebtNotFoundException() {
        thrown.expect(DebtNotFoundException.class);
        debts.remove(DEBT_A);
    }

    @Test
    public void remove_existingDebt_removesDebt() {
        debts.add(DEBT_A);
        debts.remove(DEBT_A);
        UniqueDebtList expectedUniqueDebtList = new UniqueDebtList();
        assertEquals(expectedUniqueDebtList, debts);
    }

    @Test
    public void setDebts_nullUniqueDebtList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        debts.setDebts((UniqueDebtList) null);
    }

    @Test
    public void setDebts_uniqueDebtList_replacesOwnListWithProvidedUniqueDebtList() {
        debts.add(DEBT_A);
        UniqueDebtList expectedUniqueDebtList = new UniqueDebtList();
        debts.add(DEBT_B);
        debts.setDebts(expectedUniqueDebtList);
        assertEquals(expectedUniqueDebtList, debts);
    }

    @Test
    public void setDebts_nullList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        debts.setDebts((List<Debt>) null);
    }

    @Test
    public void setDebts_list_replacesOwnListWithProvidedList() {
        debts.add(DEBT_A);
        List<Debt> debtList = Collections.singletonList(DEBT_B);
        debts.setDebts(debtList);
        UniqueDebtList expectedUniqueDebtList = new UniqueDebtList();
        expectedUniqueDebtList.add(DEBT_B);
        assertEquals(expectedUniqueDebtList, debts);
    }

    @Test
    public void setDebts_listWithDuplicateDebts_throwsDuplicateDebtException() {
        List<Debt> listWithDuplicateDebts = Arrays.asList(DEBT_A, DEBT_A);
        thrown.expect(DuplicateDebtException.class);
        debts.setDebts(listWithDuplicateDebts);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        debts.asUnmodifiableObservableList().remove(0);
    }

}
