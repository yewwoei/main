package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.jio.Jio;
import seedu.address.model.restaurant.Restaurant;

import java.net.URL;

/**
 * An UI component that displays information of a {@code Restaurant}.
 */
public class JioCard extends UiPart<Region> {

    private static final String FXML = "RestaurantListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Jio jio;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private FlowPane tags;

    public JioCard(Jio jio, int displayedIndex) {
        super(FXML);
        this.jio = jio;
        id.setText(displayedIndex + ". ");
        name.setText(jio.getName().fullName);
        address.setText(jio.getLocation().value);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RestaurantCard)) {
            return false;
        }

        // state check
        JioCard card = (JioCard) other;
        return id.getText().equals(card.id.getText())
                && jio.equals(card.jio);
    }
}


