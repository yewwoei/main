package seedu.address.model.jio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.restaurant.Address;
import seedu.address.model.timetable.Date;
import seedu.address.model.user.Name;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;


/**
 * Represents a Jio in the jiobook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Jio {
    private Name name;
    private Date date;
    private Address location;
    private List<User> people;

    public Jio(Name name, Date date, Address location, User creator) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.people = new ArrayList<>();
        this.people.add(creator);
    }

    public Jio(Name name, Date date, Address location) {
        this.name = name;
        this.date = date;
        this.location = location;
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

    public List<User> getPeople() { return people;}
    
    public void hasUser(User newUser) {
        this.people.stream().anyMatch(user -> newUser.equals(user));
    }

    public void addUsers(User newUser) {
        this.people.add(newUser);
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
