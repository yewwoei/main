package seedu.address.ui;

import static seedu.address.testutil.TypicalRestaurants.RESTAURANT_A;

import org.junit.Before;

import seedu.address.commons.events.ui.RestaurantPanelSelectionChangedEvent;

import guitests.guihandles.BrowserPanelHandle;

public class BrowserPanelTest extends GuiUnitTest {
    private RestaurantPanelSelectionChangedEvent selectionChangedEventStub;

    private BrowserPanel browserPanel;
    private BrowserPanelHandle browserPanelHandle;

    @Before
    public void setUp() {
        selectionChangedEventStub = new RestaurantPanelSelectionChangedEvent(RESTAURANT_A);

        guiRobot.interact(() -> browserPanel = new BrowserPanel());
        uiPartRule.setUiPart(browserPanel);

        browserPanelHandle = new BrowserPanelHandle(browserPanel.getRoot());
    }
}
