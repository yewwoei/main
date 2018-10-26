package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.jio.Jio;

/**
 * Represents a selection change in the Restaurant List Panel
 */
public class JioPanelSelectionChangedEvent extends BaseEvent {
    
    private final Jio newSelection;

    public JioPanelSelectionChangedEvent(Jio newSelection) {
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public Jio getNewSelection() {
        return newSelection;
    }
}

