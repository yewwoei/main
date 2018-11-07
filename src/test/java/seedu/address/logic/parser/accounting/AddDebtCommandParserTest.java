package seedu.address.logic.parser.accounting;

import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalUsers.getTypicalUsers;

import org.junit.Test;
import seedu.address.logic.commands.accounting.AddDebtCommand;
import seedu.address.model.accounting.Amount;

public class AddDebtCommandParserTest {

    private AddDebtCommandParser parser = new AddDebtCommandParser();
    public static final String VALID_USER_A = " " + PREFIX_USERNAME + getTypicalUsers().get(0).getUsername().toString();
    public static final String VALID_USER_B = " " + PREFIX_USERNAME + getTypicalUsers().get(1).getUsername().toString();
    public static final String VALID_AMOUNT = " " + PREFIX_AMOUNT + "13";


    @Test
    public void parse_allFieldsPresent_success() {

        assertParseSuccess(parser, VALID_USER_A + VALID_AMOUNT,
                new AddDebtCommand(getTypicalUsers().get(0).getUsername(), new Amount("13")));

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_USER_A + VALID_AMOUNT,
                new AddDebtCommand(getTypicalUsers().get(0).getUsername(), new Amount("13")));
    }



}
