package org.example.pages.menues;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.proxy.NotImplementedException;
import org.example.pages.BaseScreen;
import org.example.pages.ShopScreen;
import org.openqa.selenium.WebElement;

import static org.example.core.utils.WaitUtils.waitForVisibleShort;

public class PSPTabBar extends BaseScreen {

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[contains(@content-desc, 'Shop')]")
    private WebElement shopTab;

    public static PSPTabBar initPage() {
        return (PSPTabBar) easyInitPage(new PSPTabBar());
    }

    public enum PSPTab {
        PLAN, SHOP, PANTRY;
    }

    public BaseScreen opensScreenViaPSPTabBar(PSPTab menuItem) {
        switch (menuItem) {
            case SHOP:
                return openShopScreen(menuItem);
            default:
                // Not implemented for other bottom menu buttons
                throw new NotImplementedException();
        }
    }

    private ShopScreen openShopScreen(PSPTab bottomMenuItem) {
        waitForVisibleShort(shopTab).click();
        ShopScreen shopScreen = new ShopScreen().initPage();
        shopScreen.verifyPageOpened();
        return shopScreen;
    }

}





