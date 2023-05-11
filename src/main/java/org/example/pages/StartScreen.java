package org.example.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class StartScreen extends BaseScreen {

    @AndroidFindBy(id = "com.yummly.android:id/skip_view")
    private WebElement skipButton;

    public static StartScreen initPage() {
        return (StartScreen) easyInitPage(new StartScreen());
    }

    public void clickSkipPersonalizationButton() {
        skipButton.click();
    }
}
