package seedu.address.model.restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Restaurant's reviews in the address book.
 */
public class Reviews {
    
    private List<UserReview> userReviewList;
    private float restaurantRating;

    /**
     * Constructs {@code Reviews}.
     */
    public Reviews() {
        userReviewList = new ArrayList<>();
    }
    
    public void addUserReview(UserReview userReview) {
        userReviewList.add(userReview);
        restaurantRating = (restaurantRating + (float) userReview.getRating()) / userReviewList.size();
    }
    
    public float getRestaurantRating() {
        return restaurantRating;
    }
    

}


