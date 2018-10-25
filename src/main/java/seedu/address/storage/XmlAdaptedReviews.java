package seedu.address.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.restaurant.Reviews;
import seedu.address.model.restaurant.UserReview;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JAXB-friendly adapted version of the Reviews.
 */
public class XmlAdaptedReviews {

    @XmlElement(required = true)
    private String rating;

    @XmlElement(required = true)
    private List<XmlAdaptedUserReview> userReviews = new ArrayList<>();
    
    /**
     * Constructs a XmlAdaptedReviews.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedReviews() {}

    /**
     * Constructs a {@code XmlAdaptedReviews} with the given review details.
     */
    public XmlAdaptedReviews(String rating, List<XmlAdaptedUserReview> userReviews) {
        this.rating = rating;
        if (userReviews != null) {
            this.userReviews = new ArrayList<>(userReviews);
        }
    }

    /**
     * Converts Reviews into this class for JAXB use.
     */
    public XmlAdaptedReviews(Reviews reviews) {
        rating = reviews.getRestaurantRatingValue();
        userReviews = reviews.getUserReviewList().stream()
                .map(XmlAdaptedUserReview::new)
                .collect(Collectors.toList());
    }

    /**
     * Converts this jaxb-friendly adapted reviews object into the model's Reviews object.
     */
    public Reviews toModelType() throws IllegalValueException {
        final List<UserReview> restaurantUserReviews = new ArrayList<>();
        for (XmlAdaptedUserReview userReview : userReviews) {
            restaurantUserReviews.add(userReview.toModelType());
        }
        return new Reviews(rating, restaurantUserReviews);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedReviews)) {
            return false;
        }

        return rating.equals(((XmlAdaptedReviews) other).rating)
                && userReviews.equals(((XmlAdaptedReviews) other).userReviews);
    }
}
