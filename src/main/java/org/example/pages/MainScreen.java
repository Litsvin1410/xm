package org.example.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.WithTimeout;
import org.example.core.utils.WaitUtils;
import org.openqa.selenium.WebElement;

import java.time.temporal.ChronoUnit;

public class MainScreen extends BaseScreen {

    @WithTimeout(time = 30, chronoUnit = ChronoUnit.SECONDS)
    @AndroidFindBy(id = "com.yummly.android:id/item_image")
    private WebElement feedItem;

    public static MainScreen initPage() {
        return (MainScreen) easyInitPage(new MainScreen());
    }

    public void waitTillFeedIsLoaded() {
        WaitUtils.waitForVisibleMedium(feedItem);
    }
}
