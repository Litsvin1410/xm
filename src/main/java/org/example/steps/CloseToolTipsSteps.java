package org.example.steps;

import org.example.core.IConstants;
import org.example.core.utils.WaitUtils;
import org.example.pages.MainScreen;
import org.example.pages.SearchScreen;
import org.example.pages.menues.BottomMenu;

public class CloseToolTipsSteps implements IConstants {

    private static final int AMOUNT_OF_TOOL_TIPS_ON_MAIN_SCREEN = 3;
    private static final int AMOUNT_OF_TOOL_TIPS_ON_SEARCH_SCREEN = 1;

    public void closeToolTipsOnSearchScreen() {
        SearchScreen.initPage().verifyPageOpened();
        clickFewTimesAtBottomMenuItem(AMOUNT_OF_TOOL_TIPS_ON_SEARCH_SCREEN);
    }

    public void closeToolTipsOnMainScreen() {
        MainScreen.initPage().waitTillFeedIsLoaded();
        clickFewTimesAtBottomMenuItem(AMOUNT_OF_TOOL_TIPS_ON_MAIN_SCREEN);
    }

    private void clickFewTimesAtBottomMenuItem(int amountOfClicks) {
        BottomMenu bottomMenu = BottomMenu.initPage();
        for (int i = 0; i < amountOfClicks; i++) {
            bottomMenu.clickBottomMenuItem(BottomMenu.BottomMenuItem.SEARCH);
            WaitUtils.wait(ONE);
        }
    }

}
