package seedu.address.model.user;

public class Friendship {
    private FriendshipStatus friendshipStatus;
    private User friendUser;
    private User initiatedBy;

    public Friendship(User friendUser, User initiatedBy) {
        this.friendUser = friendUser;
        this.friendshipStatus = FriendshipStatus.PENDING;
        this.initiatedBy = initiatedBy;
    }

    public Friendship(User friendUser, User initiatedBy, FriendshipStatus friendshipStatus) {
        this.friendUser = friendUser;
        this.friendshipStatus = friendshipStatus;
        this.initiatedBy = initiatedBy;
    }

    public void changeFriendshipStatus() {
        this.friendshipStatus = FriendshipStatus.ACCEPTED;
    }

    public FriendshipStatus getFrienshipStatus() {
        return this.friendshipStatus;
    }

    public User getFriendUser() {
        return this.friendUser;
    }

    public User getInitiatedBy() {
        return this.initiatedBy;
    }
}
