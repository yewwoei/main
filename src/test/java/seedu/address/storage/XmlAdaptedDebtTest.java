package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.storage.XmlAdaptedDebt.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.storage.XmlAdaptedDebt.NOT_EXIST_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.TypicalDebts.DEBT_A;
import static seedu.address.testutil.TypicalUsers.getTypicalUserData;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.accounting.Debt;
import seedu.address.testutil.Assert;

public class XmlAdaptedDebtTest {


    private static final String INVALID_USER = "lolol";
    private static final String INVALID_AMOUNT_1 = "9999999999999999999999999999";
    private static final String INVALID_AMOUNT_2 = "-3";


    private static final String VALID_USER_1 = "benny123";
    private static final String VALID_USER_2 = "carrlymaximus";
    private static final String VALID_AMOUNT = "13";
    private static final String VALID_STATUS = "PENDING";
    private static final String VALID_ID = "181031143644666";


    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void toModelType_validDebtDetails_returnsDebt() throws Exception {
        XmlAdaptedDebt debt = new XmlAdaptedDebt(DEBT_A);
        assertEquals(DEBT_A, debt.toModelType(getTypicalUserData().getUsernameUserHashMap()));
    }

    @Test
    public void toModelType_invalidCreditor_throwsIllegalValueException() throws Exception {
        XmlAdaptedDebt debt =
                new XmlAdaptedDebt(INVALID_USER, VALID_USER_1, VALID_AMOUNT, VALID_ID, VALID_STATUS);
        String expectedMessage = String.format(NOT_EXIST_FIELD_MESSAGE_FORMAT, Debt.class.getSimpleName());
        thrown.expect(IllegalValueException.class);
        Assert.assertThrows(IllegalValueException.class, expectedMessage,
                (Assert.VoidCallable) debt.toModelType(getTypicalUserData().getUsernameUserHashMap()));
    }

    @Test
    public void toModelType_nullCreditor_throwsIllegalValueException() throws Exception {
        XmlAdaptedDebt debt =
                new XmlAdaptedDebt(null, VALID_USER_1, VALID_AMOUNT, VALID_ID, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Debt.class.getSimpleName());
        thrown.expect(IllegalValueException.class);
        Assert.assertThrows(IllegalValueException.class, expectedMessage,
                (Assert.VoidCallable) debt.toModelType(getTypicalUserData().getUsernameUserHashMap()));
    }

    @Test
    public void toModelType_invalidDebtor_throwsIllegalValueException() throws Exception {
        XmlAdaptedDebt debt =
                new XmlAdaptedDebt(VALID_USER_1, INVALID_USER, VALID_AMOUNT, VALID_ID, VALID_STATUS);
        String expectedMessage = String.format(NOT_EXIST_FIELD_MESSAGE_FORMAT, Debt.class.getSimpleName());
        thrown.expect(IllegalValueException.class);
        Assert.assertThrows(IllegalValueException.class, expectedMessage,
                (Assert.VoidCallable) debt.toModelType(getTypicalUserData().getUsernameUserHashMap()));
    }

    @Test
    public void toModelType_nullDebtor_throwsIllegalValueException() throws Exception {
        XmlAdaptedDebt debt =
                new XmlAdaptedDebt(VALID_USER_1, null, VALID_AMOUNT, VALID_ID, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Debt.class.getSimpleName());
        thrown.expect(IllegalValueException.class);
        Assert.assertThrows(IllegalValueException.class, expectedMessage,
                (Assert.VoidCallable) debt.toModelType(getTypicalUserData().getUsernameUserHashMap()));
    }

    @Test
    public void toModelType_invalidAmount1_throwsIllegalValueException() throws Exception {
        XmlAdaptedDebt debt =
                new XmlAdaptedDebt(VALID_USER_1, VALID_USER_2, INVALID_AMOUNT_1, VALID_ID, VALID_STATUS);
        String expectedMessage = String.format(NOT_EXIST_FIELD_MESSAGE_FORMAT, Debt.class.getSimpleName());
        thrown.expect(IllegalValueException.class);
        Assert.assertThrows(IllegalValueException.class, expectedMessage,
                (Assert.VoidCallable) debt.toModelType(getTypicalUserData().getUsernameUserHashMap()));
    }

    @Test
    public void toModelType_invalidAmount2_throwsIllegalValueException() throws Exception {
        XmlAdaptedDebt debt =
                new XmlAdaptedDebt(VALID_USER_1, VALID_USER_2, INVALID_AMOUNT_2, VALID_ID, VALID_STATUS);
        String expectedMessage = String.format(NOT_EXIST_FIELD_MESSAGE_FORMAT, Debt.class.getSimpleName());
        thrown.expect(IllegalValueException.class);
        Assert.assertThrows(IllegalValueException.class, expectedMessage,
                (Assert.VoidCallable) debt.toModelType(getTypicalUserData().getUsernameUserHashMap()));
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() throws Exception {
        XmlAdaptedDebt debt =
                new XmlAdaptedDebt(VALID_USER_1, VALID_USER_2, null, VALID_ID, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Debt.class.getSimpleName());
        thrown.expect(IllegalValueException.class);
        Assert.assertThrows(IllegalValueException.class, expectedMessage,
                (Assert.VoidCallable) debt.toModelType(getTypicalUserData().getUsernameUserHashMap()));
    }
}
