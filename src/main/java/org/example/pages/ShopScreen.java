package org.example.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.example.core.utils.WaitDuration;
import org.example.core.utils.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static org.example.core.DriverFactory.getDriver;

public class ShopScreen extends BaseScreen {


    @AndroidFindBy(id = "com.yummly.android:id/shoppinglist_header_title")
    private WebElement snackFoodsHeader;

    @AndroidFindBy(id = "com.yummly.android:id/category_size")
    private WebElement snackFoodsListSize;

    @AndroidFindBy(id = "com.yummly.android:id/expandable_text")
    private ArrayList<WebElement> snackFoodsItems;

    @AndroidFindBy(xpath = "(//*[@resource-id = 'com.yummly.android:id/backView']/android.widget.LinearLayout)[1]")
    private WebElement ingredientRecipeButton;

    public static ShopScreen initPage() {
        return (ShopScreen) easyInitPage(new ShopScreen());
    }

    public void verifyPageOpened() {
        WaitUtils.waitForVisibleShort(snackFoodsHeader);
    }

    public String getAmountOfFoodSnacksInList(){
        return snackFoodsListSize.getText().replace("(", "").replace(")", "");
    }

    public String getFirstSnackName(){
        return snackFoodsItems.get(0).getText();
    }

    public ShopScreen expandIngredientByName(String ingredientName) {
        String xpath = "//*[@text = '%s']/../../following-sibling::*[@resource-id = 'com.yummly.android:id/expand_menu']";
        WebDriverWait wait = new WebDriverWait(getDriver(), WaitDuration.SHORT.getDuration());
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(String.format(xpath, ingredientName)))).click();
        return this;
    }

    public RecipeScreen clickRecipeButtonOnExpandedIngredient(){
        ingredientRecipeButton.click();
        return RecipeScreen.initPage();
    }

}
