package seedu.address.model.restaurant;

import seedu.address.model.user.Username;

public class UserReview {

    private Rating rating;
    private Username username;
    private WrittenReview review;

    public UserReview(Username username, Rating rating, WrittenReview review) {
        this.username = username;
        this.rating = rating;
        this.review = review;
    }
    
    public int getRating() {
        return rating.rating;
    }
}
