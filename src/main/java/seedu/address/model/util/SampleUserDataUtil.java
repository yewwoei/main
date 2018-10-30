package seedu.address.model.util;

import seedu.address.model.Name;
import seedu.address.model.UserData;
import seedu.address.model.restaurant.Rating;
import seedu.address.model.restaurant.WrittenReview;
import seedu.address.model.user.Email;
import seedu.address.model.user.Password;
import seedu.address.model.user.Phone;
import seedu.address.model.user.RestaurantReview;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleUserDataUtil {

    public static final RestaurantReview navekom_reviews01 = new RestaurantReview(
            new seedu.address.model.restaurant.Name("Waa Cow"), new Rating(3),
            new WrittenReview("I tried the Ultimate Beef Sushi and they were delicious."));

    public static final RestaurantReview chelchia_reviews01 = new RestaurantReview(
            new seedu.address.model.restaurant.Name("Waa Cow"), new Rating(2),
            new WrittenReview("I tried the Udon Cirashi and they were so-so."));

    public static final RestaurantReview chelchia_reviews02 = new RestaurantReview(
            new seedu.address.model.restaurant.Name("Subway"), new Rating(5),
            new WrittenReview("Namaste. Subway nice nice. Good food."));

    public static final RestaurantReview meena567_reviews01 = new RestaurantReview(
            new seedu.address.model.restaurant.Name("Waa Cow"), new Rating(2),
            new WrittenReview("Definitely coming back."));

    public static final RestaurantReview meena567_reviews02 = new RestaurantReview(
            new seedu.address.model.restaurant.Name("Subway"), new Rating(4),
            new WrittenReview("Subway give fresh food."));

    public static final RestaurantReview katespades_reviews01 = new RestaurantReview(
            new seedu.address.model.restaurant.Name("Waa Cow"), new Rating(2),
            new WrittenReview("The maki was fantastic."));

    public static final RestaurantReview katespades_reviews02 = new RestaurantReview(
            new seedu.address.model.restaurant.Name("Subway"), new Rating(5),
            new WrittenReview("5 stars sub."));

    public static final RestaurantReview themyth_reviews01 = new RestaurantReview(
            new seedu.address.model.restaurant.Name("Subway"), new Rating(5),
            new WrittenReview("Subway eat fresh."));

    public static final RestaurantReview themyth_reviews02 = new RestaurantReview(
            new seedu.address.model.restaurant.Name("The Royals Bistro"), new Rating(5),
            new WrittenReview("I stalked LHL here."));

    public static final RestaurantReview brotherLoong_reviews01 = new RestaurantReview(
            new seedu.address.model.restaurant.Name("The Royals Bistro"), new Rating(5),
            new WrittenReview("The food here is 5 stars. Just like the Singapore flag."));

    public static User[] getSampleUsers() {
        return new User[] {
                new User(new Username("navekom"), new Password("pwwd123"),
                        new Name("Evan Mok"), new Phone("91234567"),
                        new Email("evanm@gmail.com"))
                        .addRestaurantReviewToUser(navekom_reviews01),
                new User(new Username("meena567"), new Password("meenapwd123"),
                        new Name("Meena"), new Phone("98834568"),
                        new Email("meena567@gmail.com"))
                        .addRestaurantReviewToUser(meena567_reviews01)
                        .addRestaurantReviewToUser(meena567_reviews02),
                new User(new Username("chelchia"), new Password("chelpwd123"),
                        new Name("Chelsea Chia"), new Phone("90134569"),
                        new Email("chelchia@gmail.com"))
                        .addRestaurantReviewToUser(chelchia_reviews01)
                        .addRestaurantReviewToUser(chelchia_reviews02),
                new User(new Username("katespades"), new Password("katepwd123"),
                        new Name("Kate Ng"), new Phone("91434567"),
                        new Email("kateSpades@gmail.com"))
                        .addRestaurantReviewToUser(katespades_reviews01)
                        .addRestaurantReviewToUser(katespades_reviews02),
                new User(new Username("aideeeen"), new Password("yewwoei123"),
                        new Name("Low Yew Woei"), new Phone("91765567"),
                        new Email("yewwoei@gmail.com")),
                new User(new Username("themyth"), new Password("themyth2k18"),
                        new Name("AP Damith"), new Phone("91134567"),
                        new Email("damith@nus.edu.com"))
                        .addRestaurantReviewToUser(themyth_reviews01)
                        .addRestaurantReviewToUser(themyth_reviews02),
                new User(new Username("brotherLoong"), new Password("LHL2k18"),
                        new Name("Hsien Loong"), new Phone("80232567"),
                        new Email("leehl@pmo.com"))
                        .addRestaurantReviewToUser(brotherLoong_reviews01),
                new User(new Username("thejrlinguist"), new Password("kengji123"),
                        new Name("KengJi"), new Phone("95534567"),
                        new Email("kengji@gmail.com"))
        };
    }

    public static UserData getSampleUserData() {
        UserData sampleUd = new UserData();
        for (User sampleUser : getSampleUsers()) {
            sampleUd.addUser(sampleUser);
        }
        return sampleUd;
    }
}
