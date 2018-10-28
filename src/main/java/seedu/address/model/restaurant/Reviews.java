package seedu.address.model.restaurant;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Restaurant's reviews in the address book.
 */
public class Reviews {

    private List<UserReview> userReviewList;
    private double restaurantRating;
    private String restaurantRatingValue;
    private static DecimalFormat df = new DecimalFormat("#.00");

    /**
     * Constructs {@code Reviews}.
     */
    public Reviews() {
        userReviewList = new ArrayList<>();
        restaurantRating = 0.00;
        this.restaurantRatingValue = "0.00";
    }

    /**
     * Constructs {@code Reviews}.
     * 
     * @param restaurantRatingValue The overall rating of a restaurant.
     * @param userReviewList A list of UserReview.
     */
    public Reviews(String restaurantRatingValue, List<UserReview> userReviewList) {
        this.userReviewList = userReviewList;
        if (restaurantRatingValue.equals("0.00")) {
            this.restaurantRating = 0.00;
            this.restaurantRatingValue = "0.00";
        } else {
            this.restaurantRating = Double.parseDouble(restaurantRatingValue);
            this.restaurantRatingValue = df.format(restaurantRating);
        }
    }

    
    public String getRestaurantRatingValue() {
        return restaurantRatingValue;
    }

    public List<UserReview> getUserReviewList() {
        return userReviewList;
    }

    /**
     * Adds a User Review to the Reviews Object of a restaurant.
     * 
     * The restaurantRatingValue of a restaurant is the average rating of a restaurant and
     * is updated each time a {@code userReview} is added.
     */
    public Reviews addUserReview(UserReview userReview) {
        userReviewList.add(userReview);
        if (userReviewList.size() == 1) {
            restaurantRating = (double) userReview.getRating();
            this.restaurantRatingValue = df.format(restaurantRating);
            return this;
        }
        restaurantRating = restaurantRating + (double) userReview.getRating();
        Double tempCalculation = restaurantRating / userReviewList.size();
        this.restaurantRatingValue = df.format(tempCalculation);
        return this;
    }

}
