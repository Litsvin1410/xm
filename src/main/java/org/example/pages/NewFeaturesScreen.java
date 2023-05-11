package org.example.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.example.core.utils.SwipeUtils;
import org.example.core.utils.WaitUtils;
import org.openqa.selenium.WebElement;

public class NewFeaturesScreen extends BaseScreen {

    @AndroidFindBy(id = "com.yummly.android:id/descriptionTextView")
    private WebElement newFeatureDescription;

    @AndroidFindBy(id = "com.yummly.android:id/closeButton")
    private WebElement closeButton;

    public static NewFeaturesScreen initPage() {
        return (NewFeaturesScreen) easyInitPage(new NewFeaturesScreen());
    }

    public String getFeatureDescription() {
        return newFeatureDescription.getText();
    }

    public boolean isOpened() {
        return WaitUtils.isElementVisibleMediumNonFailing(newFeatureDescription);
    }

    public void swipeToNextNewFeature() {
        SwipeUtils.swipeScreen(SwipeUtils.SwipeDirection.SWIPE_LEFT);
    }

    public void closeNewFeaturesDescriptionScreen() {
        closeButton.click();
    }

}




