package seedu.address.model.jio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.model.Name;
import seedu.address.model.restaurant.Address;
import seedu.address.model.timetable.Date;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;

import static java.util.Objects.requireNonNull;


/**
 * Represents a Jio in the jiobook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Jio {
    private Name name;
    private Date date;
    private Address location;
    private List<Username> people;
    private Username creator;
    private Optional<Name> groupName = Optional.empty();

    public Jio() {}

    public Jio(Name name, Date date, Address location, Username creator) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.people = new ArrayList<>();
        this.people.add(creator);
        this.creator = creator;
        this.people = new ArrayList<>();
        this.people.add(creator);
    }

    public Jio(Name name, Date date, Address location) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.people = new ArrayList<>();
        this.creator = null;
    }

    public Jio(Name name, Date date, Address location, Name groupName) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.people = new ArrayList<>();
        this.creator = null;
        this.groupName = Optional.of(groupName);
    }

    public Jio(Name name, Date date, Address location, List<Username> people, Username creator) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.people = new ArrayList<>(people);
        this.creator = creator;
    }

    public Name getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Address getLocation() {
        return location;
    }

    public List<Username> getPeople() {
        return people;
    }

    public Username getCreator() {
        return creator;
    }

    public Name getGroupName() {
        return groupName.get();
    }

    public boolean hasUser(User newUser) {
        requireNonNull(newUser);
        return this.people.stream().anyMatch(user -> newUser.getUsername().equals(user));
    }

    /**
     * Adds a user to the list of people (ie.Username) in the jio.
     * @param newUser user to be added
     */
    public void addUser(User newUser) {
        requireNonNull(newUser);
        if (!this.hasUser(newUser)) {
            this.people.add(newUser.getUsername());
        }
    }

    public void setCreator(User user) {
        requireNonNull(user);
        this.creator = user.getUsername();
    }

    public boolean isGroupJio() {
        return this.groupName.isPresent();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof Jio)) {
            return false;
        }
        // state check
        Jio otherJio = (Jio) other;
        return otherJio.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, date, location);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Date: ")
                .append(getDate())
                .append(" Location: ")
                .append(getLocation())
                .append(" People: ");
        this.getPeople().forEach(x -> builder.append(x));
        return builder.toString();
    }

}
