package seedu.address.logic.parser.group;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalFriendships.FRIENDSHIP_1;

import org.junit.Test;

import seedu.address.logic.commands.friend.DeleteFriendRequestCommand;
import seedu.address.logic.parser.friend.DeleteFriendRequestCommandParser;
import seedu.address.model.user.Username;

public class DeleteFriendRequestCommandParserTest {
    private DeleteFriendRequestCommandParser parser = new DeleteFriendRequestCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteFriendCommand() {
        final String UsernameString = " " + PREFIX_USERNAME + FRIENDSHIP_1.getFriendUsername().toString();
        final Username UsernameToDelete = FRIENDSHIP_1.getFriendUser().getUsername();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + UsernameString,
                new DeleteFriendRequestCommand(UsernameToDelete));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteFriendRequestCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "hello", expectedMessage);
    }
}
