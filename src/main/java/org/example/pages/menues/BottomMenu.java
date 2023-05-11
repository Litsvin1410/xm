package org.example.pages.menues;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.proxy.NotImplementedException;
import org.example.pages.BaseScreen;
import org.example.pages.SearchScreen;
import org.openqa.selenium.WebElement;

import static org.example.core.utils.WaitUtils.waitForVisibleShort;

public class BottomMenu extends BaseScreen {

    @AndroidFindBy(id = "com.yummly.android:id/nav_graph_search")
    private WebElement searchButton;

    public static BottomMenu initPage() {
        return (BottomMenu) easyInitPage(new BottomMenu());
    }

    public enum BottomMenuItem {
        SEARCH;
    }

    // For opening pages using bottom menu
    public BaseScreen openPageFromBottomMenu(BottomMenuItem menuItem) {
        switch (menuItem) {
            case SEARCH:
                return openSearchScreen(menuItem);
            default:
                // Not implemented for other bottom menu buttons
                throw new NotImplementedException();
        }
    }

    // For clicking bottom menu items for some reasons
    public void clickBottomMenuItem(BottomMenuItem menuItem) {
        switch (menuItem) {
            case SEARCH:
                waitForVisibleShort(searchButton).click();
                break;
            default:
                // Not implemented for other bottom menu buttons
                throw new NotImplementedException();
        }
    }

    private SearchScreen openSearchScreen(BottomMenuItem bottomMenuItem) {
        waitForVisibleShort(searchButton).click();
        SearchScreen searchScreen = new SearchScreen().initPage();
        searchScreen.verifyPageOpened();
        return searchScreen;
    }

}





