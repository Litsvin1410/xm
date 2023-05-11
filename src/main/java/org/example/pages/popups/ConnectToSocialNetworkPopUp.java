package org.example.pages.popups;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.example.pages.BaseScreen;
import org.openqa.selenium.WebElement;

public class ConnectToSocialNetworkPopUp extends BaseScreen {

    @AndroidFindBy(id = "com.yummly.android:id/alert_close_button")
    private WebElement closePopUpButton;

    public static ConnectToSocialNetworkPopUp initPage() {
        return (ConnectToSocialNetworkPopUp) easyInitPage(new ConnectToSocialNetworkPopUp());
    }

    public void closeConnectionToSocialNetworkPopUp() {
        closePopUpButton.click();
    }

}




