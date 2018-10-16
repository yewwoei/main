package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditRestaurantDescriptor;
import seedu.address.logic.commands.user.WriteReviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new WriteReview object
 */
public class WriteReviewCommandParser implements Parser<WriteReviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the WriteReviewCommand
     * and returns an WriteReviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public WriteReviewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RATING, PREFIX_REVIEW);

        Index index;

        try {
            index = ParserRestaurantUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    WriteReviewCommand.MESSAGE_USAGE), pe);
        }

        float rating = ParserRestaurantUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserRestaurantUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Address address = ParserRestaurantUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserRestaurantUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Restaurant restaurant = new Restaurant(name, phone, address, tagList);

        return new AddCommand(restaurant);

        return new WriteReviewCommand(index, editRestaurantDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserRestaurantUtil.parseTags(tagSet));
    }

}
