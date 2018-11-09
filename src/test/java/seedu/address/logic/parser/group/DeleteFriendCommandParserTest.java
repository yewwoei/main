package seedu.address.logic.parser.group;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalFriendships.FRIENDSHIP_1;

import org.junit.Test;

import seedu.address.logic.commands.friend.DeleteFriendCommand;
import seedu.address.logic.parser.friend.DeleteFriendCommandParser;
import seedu.address.model.user.Username;

public class DeleteFriendCommandParserTest {
    private DeleteFriendCommandParser parser = new DeleteFriendCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteFriendCommand() {
        final String usernameString = " " + PREFIX_USERNAME + FRIENDSHIP_1.getFriendUsername().toString();
        final Username usernameToDelete = FRIENDSHIP_1.getFriendUser().getUsername();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + usernameString,
                new DeleteFriendCommand(usernameToDelete));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteFriendCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "hello", expectedMessage);
    }
}
