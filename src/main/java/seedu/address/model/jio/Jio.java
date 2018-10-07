package seedu.address.model.jio;

import java.util.Objects;

import seedu.address.model.restaurant.Address;
import seedu.address.model.restaurant.Name;


/**
 * Represents a Jio in the jiobook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Jio {
    private Name name;
    private Time time;
    private Date date;
    private Address location;
    //private UniqueUserList<User> people;

    public Jio(Name name, Time time, Date date, Address location) {
        this.name = name;
        this.time = time;
        this.date = date;
        this.location = location;
        //this.people = new UniqueUserList();
        //this.people.add(creator);
    }

    public String getName() {
        return name.toString();
    }

    public String getTime() {
        return time.toString();
    }

    public String getDate() {
        return date.toString();
    }

    public String getLocation() {
        return location.toString();
    }

    /*
    public ObservableList<User> getPeople() { return people.asUnmodifiableObservableList();}
    public void addUsers(User newUser) {
        this.people.add(newUser);
    }
    */

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
        return Objects.hash(name, time, date, location);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Time: ")
                .append(getTime())
                .append(" Date: ")
                .append(getDate())
                .append(" Location: ")
                .append(getLocation());
        //this.getPeople().forEach(x -> builder.append(x.getName()));
        return builder.toString();
    }

}
