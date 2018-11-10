package seedu.address.model.restaurant;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Restaurant's reviews in the address book.
 */
public class Reviews {

    private static DecimalFormat df = new DecimalFormat("#.00");
    private List<UserReview> userReviewList;
    private double totalRatings;
    private String restaurantRatingValue;

    /**
     * Constructs {@code Reviews}.
     */
    public Reviews() {
        userReviewList = new ArrayList<>();
        totalRatings = 0.00;
        this.restaurantRatingValue = "0.00";
    }

    /**
     * Constructs {@code Reviews}.
     *
     * @param restaurantRatingValue The overall rating of a restaurant.
     * @param userReviewList A list of UserReview.
     */
    public Reviews(String restaurantRatingValue, double totalRatings, List<UserReview> userReviewList) {
        this.userReviewList = userReviewList;
        this.totalRatings = totalRatings;
        if (restaurantRatingValue.equals("0.00")) {
            this.totalRatings = 0.00;
            this.restaurantRatingValue = "0.00";
        } else {
            this.totalRatings = Double.parseDouble(restaurantRatingValue);
            this.restaurantRatingValue = df.format(totalRatings);
        }
    }

    public String getRestaurantRatingValue() {
        return restaurantRatingValue;
    }

    public String getRestaurantTotalRatings() {
        return Double.toString(totalRatings);
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
            totalRatings = (double) userReview.getRating();
            this.restaurantRatingValue = df.format(totalRatings);
            return this;
        }
        totalRatings = totalRatings + (double) userReview.getRating();
        Double tempCalculation = totalRatings / userReviewList.size();
        this.restaurantRatingValue = df.format(tempCalculation);
        return this;
    }
}
