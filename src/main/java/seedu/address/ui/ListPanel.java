package seedu.address.ui;

import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.JioPanelSelectionChangedEvent;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.model.jio.Jio;

import java.util.logging.Logger;

/**
 * Panel containing the list of restaurants.
 */
public class ListPanel<T> extends UiPart<Region> {
    private static final String FXML = "JioListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ListPanel.class);

    @javafx.fxml.FXML
    private ListView<T> listView;

    public ListPanel(ObservableList<T> list) {
        super(FXML);
        setConnections(list);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<T> list) {
        listView.setItems(list);
        listView.setCellFactory(listView -> new JioListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void setEventHandlerForSelectionChangeEvent() {
        listView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in restaurant list panel changed to : '" + newValue + "'");
                        raise(new JioPanelSelectionChangedEvent(newValue));
                    }
                });
    }

    /**
     * Scrolls to the {@code RestaurantCard} at the {@code index} and selects it.
     */
    private void scrollTo(int index) {
        Platform.runLater(() -> {
            listView.scrollTo(index);
            listView.getSelectionModel().clearAndSelect(index);
        });
    }

    @Subscribe
    private void handleJumpToListRequestEvent(JumpToListRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        scrollTo(event.targetIndex);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Restaurant} using a {@code RestaurantCard}.
     */
    class JioListViewCell extends ListCell<Jio> {
        @Override
        protected void updateItem(Jio jio, boolean empty) {
            super.updateItem(jio, empty);

            if (empty || jio == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new JioCard(jio, getIndex() + 1).getRoot());
            }
        }
    }

}


