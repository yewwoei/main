package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.accounting.Amount;
import seedu.address.model.accounting.DebtId;
import seedu.address.model.accounting.DebtStatus;
import seedu.address.model.group.Group;
import seedu.address.model.jio.Jio;
import seedu.address.model.restaurant.Restaurant;
import seedu.address.model.timetable.Date;
import seedu.address.model.user.Password;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;

/**
 * The API of the Model component.
 */
public interface Model {

    //=========== Model Manager Miscellaneous Methods =+==========================================================

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Restaurant> PREDICATE_SHOW_ALL_RESTAURANTS = unused -> true;

    /**
     * Clears existing backing model and replaces with the provided new data.
     */
    void resetData(ReadOnlyAddressBook newData);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a user is currently logged into the Makan Book
     */
    boolean isCurrentlyLoggedIn();

    //=========== Model Manager Restaurants Methods =+============================================================

    /**
     * Returns true if a restaurant with the same identity as {@code restaurant} exists in the address book.
     */
    boolean hasRestaurant(Restaurant restaurant);

    /**
     * Deletes the given restaurant.
     * The restaurant must exist in the address book.
     */
    void deleteRestaurant(Restaurant target);

    /**
     * Adds the given restaurant.
     * {@code restaurant} must not already exist in the address book.
     */
    void addRestaurant(Restaurant restaurant);

    /**
     * Replaces the given restaurant {@code target} with {@code editedRestaurant}.
     * {@code target} must exist in the address book.
     * The restaurant identity of {@code editedRestaurant}
     * must not be the same as another existing restaurant in the address book.
     */
    void updateRestaurant(Restaurant target, Restaurant editedRestaurant);

    //=========== Filtered Restaurant List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the filtered restaurant list
     */
    ObservableList<Restaurant> getFilteredRestaurantList();

    /**
     * Updates the filter of the filtered restaurant list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRestaurantList(Predicate<Restaurant> predicate);

    //=========== Model Manager User Methods =+===================================================================

    /**
     * Returns true if a user with the same identity as {@code user} exists in the User Data.
     */
    boolean hasUser(Username username);

    /**
     * Checks that the {@code password} matches that
     * must not be the same as another existing user in the UserData.
     */
    boolean verifyLogin(Username username, Password password);

    /**
     * Adds the given user.
     * {@code user} must not already exist in the User Data.
     */
    void addUser(User user);

    /**
     * Allows the {@code user} to be logged in immediately after they sign up.
     * There should not be anyone currently logged in before.
     */
    void loginUser(User user);

    /**
     * Allows a User to log into the account with {@code username}.
     * There should not be anyone currently logged in before.
     */
    void loginUser(Username username);

    void logoutUser();

    boolean hasUsernameSentRequest(Username friendUsername);

    boolean hasUsernameFriend(Username friendUsername);

    boolean hasUsernameFriendRequest(Username friendusername);

    void addFriend(Username friendUsername);

    void acceptFriend(Username friendUsername);

    boolean isSameAsCurrentUser(Username username);

    void deleteFriend(Username friendUsername);

    void deleteFriendRequest(Username friendUsername);

    boolean hasGroup(Group group);

    boolean hasGroupRequest(Name groupName);

    void addGroup(Group group);

    void acceptGroupRequest(Name groupName);

    boolean isInGroup(Group group);

    void addPendingUsersGroup(Group group);

    boolean isAllValidUsers(Group group);

    boolean hasUsersInGroup(Group group);

    boolean hasRequestForUsers(Group group);

    void deleteGroup(Group group);

    void deleteGroupRequest(Name groupName);

    //============ Timetable commands ==========================

    void blockDateForCurrentUser(Date date);

    void freeDateForCurrentUser(Date date);

    boolean hasDateForCurrentUser(Date date);

    //=========== Jio methods ===============================================================================

    ObservableList<Jio> getJioList();

    boolean hasJio(Jio jio);

    boolean hasJioName(Name jioName);

    void removeJioOfName(Name jioName);

    void createJio(Jio jio);

    boolean isCurrentUserInJioOfName(Name jioName);

    void addCurrentUserToJioOfName(Name jioName);

    boolean isCurrentUserCreatorOfJio(Name jioName);

    //=========== Undo/Redo/Commit ===============================================================================

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoAddressBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitAddressBook();

    //========== Model Manager Debt methods ======================================================================
    boolean hasDebtId(DebtId debtId);

    boolean matchDebtToAmount(DebtId debtId, Amount amount);

    boolean matchDebtToUser(DebtId debtId, Username user);

    boolean matchDebtToStatus(DebtId debtId, DebtStatus status);

    boolean debtExist(Username debtorUsername);

    boolean allowToClear(Username debtorUsername, Amount amount);

    void addDebt(Username debtorUsername, Amount amount);

    void clearDebt(Username debtorUsername, Amount amount);

    void acceptedDebtRequest(Username creditorUsername, Amount amount, DebtId debtId);

    void deleteDebtRequest(Username creditorUsername, Amount amount, DebtId debtId);

    String listDebtHistory();

    String listDebtor();

    String listCreditor();

    String listDebtRequestReceived();

    String listDebtRequestSent();

}

