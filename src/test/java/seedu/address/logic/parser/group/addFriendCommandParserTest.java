package seedu.address.logic.parser.group;

import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalRestaurants.BOB;

import org.junit.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.friend.AddFriendCommand;
import seedu.address.logic.parser.friend.AddFriendCommandParser;
import seedu.address.model.group.Friendship;
import seedu.address.model.user.Username;
import seedu.address.testutil.FriendshipBuilder;

import static seedu.address.testutil.TypicalFriendships.FRIENDSHIP_1;

public class addFriendCommandParserTest {
    private AddFriendCommandParser parser = new AddFriendCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Friendship expectedFriendship = new FriendshipBuilder(FRIENDSHIP_1).build();
        Username


        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddFriendCommand(expectedFriendship));
}
