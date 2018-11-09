package seedu.address.logic.parser.group;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.friend.AddFriendCommand;
import seedu.address.logic.parser.friend.AddFriendCommandParser;
import seedu.address.model.user.Username;

import static seedu.address.testutil.TypicalFriendships.FRIENDSHIP_1;

public class AddFriendCommandParserTest {
    private AddFriendCommandParser parser = new AddFriendCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        final String Username_String = " " + PREFIX_USERNAME + FRIENDSHIP_1.getMe().getUsername().toString();
        final Username Username_To_Add = FRIENDSHIP_1.getMe().getUsername();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + Username_String,
                new AddFriendCommand(Username_To_Add));
    }

    @Test
    public void parse_compulsoryFieldWrong_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFriendCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "hello", expectedMessage);
    }

}
