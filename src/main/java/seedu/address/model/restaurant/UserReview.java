package seedu.address.model.restaurant;

import seedu.address.model.user.Username;

/**
 * Represents a User's review for a restaurant in the address book.
 */
public class UserReview {

    private Rating rating;
    private Username username;
    private WrittenReview review;

    /**
     * Constructs {@code UserReview with the relevant input}.
     */
    public UserReview(Username username, Rating rating, WrittenReview review) {
        this.username = username;
        this.rating = rating;
        this.review = review;
    }

    public int getRating() {
        return rating.rating;
    }
    
    public String getUsername() {
        return username.username;
    }

    public String getWrittenReview() {
        return review.writtenReview;
    }
}
