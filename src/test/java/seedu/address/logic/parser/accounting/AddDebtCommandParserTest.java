package seedu.address.logic.parser.accounting;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalUsers.getTypicalUsers;

import org.junit.Test;
import seedu.address.logic.commands.accounting.AddDebtCommand;
import seedu.address.model.accounting.Amount;

public class AddDebtCommandParserTest {

    private AddDebtCommandParser parser = new AddDebtCommandParser();
    public static final String CREDITOR = " " + PREFIX_USERNAME + getTypicalUsers().get(0).getUsername().toString();
    public static final String DEBTOR = " " + PREFIX_USERNAME + getTypicalUsers().get(1).getUsername().toString();
    public static final String AMOUNT = " " + PREFIX_AMOUNT + "13";


    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, DEBTOR + AMOUNT,
                new AddDebtCommand(getTypicalUsers().get(1).getUsername(), new Amount("13")));
    }
}
