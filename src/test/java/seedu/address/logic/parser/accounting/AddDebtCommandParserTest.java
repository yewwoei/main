package seedu.address.logic.parser.accounting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalUsers.getTypicalUsers;

import org.junit.Test;

import seedu.address.logic.commands.accounting.AddDebtCommand;
import seedu.address.model.accounting.Amount;
import seedu.address.model.user.Username;

public class AddDebtCommandParserTest {

    public static final String VALID_USER = " " + PREFIX_USERNAME + getTypicalUsers().get(0).getUsername().toString();
    public static final String INVALID_USER = " " + PREFIX_USERNAME + "  ";
    public static final String VALID_AMOUNT = " " + PREFIX_AMOUNT + "13";
    public static final String INVALID_AMOUNT_A = " " + PREFIX_AMOUNT + "12.22222";
    public static final String INVALID_AMOUNT_B = " " + PREFIX_AMOUNT + "-17";
    private AddDebtCommandParser parser = new AddDebtCommandParser();


    @Test
    public void parse_allFieldsPresent_success() {

        assertParseSuccess(parser, VALID_USER + VALID_AMOUNT,
                new AddDebtCommand(getTypicalUsers().get(0).getUsername(), new Amount("13")));

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_USER + VALID_AMOUNT,
                new AddDebtCommand(getTypicalUsers().get(0).getUsername(), new Amount("13")));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDebtCommand.MESSAGE_USAGE);

        assertParseFailure(parser, VALID_USER, expectedMessage);

        assertParseFailure(parser, VALID_AMOUNT, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        assertParseFailure(parser, INVALID_USER + VALID_AMOUNT, Username.MESSAGE_USERNAME_CONSTRAINTS);

        assertParseFailure(parser, VALID_USER + INVALID_AMOUNT_A, Amount.MESSAGE_AMOUNT_CONSTRAINTS);

        assertParseFailure(parser, VALID_USER + INVALID_AMOUNT_B, Amount.MESSAGE_AMOUNT_CONSTRAINTS);
    }

}
