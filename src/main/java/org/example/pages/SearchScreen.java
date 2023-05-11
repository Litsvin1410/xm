package org.example.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.example.core.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static org.example.core.DriverFactory.getDriver;

public class SearchScreen extends BaseScreen {


    @AndroidFindBy(id = "com.yummly.android:id/search_src_text")
    private WebElement searchInputField;

    @AndroidFindBy(id = "com.yummly.android:id/item_title")
    private ArrayList<WebElement> resultTitles;

    public static SearchScreen initPage() {
        return (SearchScreen) easyInitPage(new SearchScreen());
    }

    public void verifyPageOpened() {
        WaitUtils.waitForVisibleShort(searchInputField);
    }

    public void searchForRecipe(String recipeName) {
        searchInputField.click();
        searchInputField.sendKeys(recipeName);
        getMobileUtils().pressEnter();
    }

    public String getFirstResultTitle() {
        return resultTitles.get(0).getText();
    }

    public boolean verifyResultsAreFound(){
        return resultTitles.size() > 0;
    }

    public void openRecipeByName(String recipeName) {
        String xpath = String.format(" //*[@text = '%s']", recipeName);
        getDriver().findElement(By.xpath(xpath)).click();
    }

}
