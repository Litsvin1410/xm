package org.example.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.example.core.IConstants;
import org.example.core.utils.SwipeUtils;
import org.example.core.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;

import static org.example.core.DriverFactory.getDriver;

public class RecipeScreen extends BaseScreen implements IConstants {

    @AndroidFindBy(xpath = "//*[@text = 'Ingredients']")
    private WebElement ingredientsTabButton;

    @AndroidFindBy(id = "com.yummly.android:id/recipe_image")
    private WebElement recipeImage;

    private static final String REPORT_ISSUE_BUTTON_XPATH = "//*[@text = 'Report Issue']";
    @AndroidFindBy(xpath = REPORT_ISSUE_BUTTON_XPATH)
    private WebElement reportIssueButton;

    private static final String EDIT_PANTRY_BUTTON_XPATH = "//*[@text = 'Edit Pantry']";
    @AndroidFindBy(xpath = EDIT_PANTRY_BUTTON_XPATH)
    private WebElement editPantryButton;

    @AndroidFindBy(id = "com.yummly.android:id/related_recipe_favorite")
    private ArrayList<WebElement> plusButtons;

    @AndroidFindBy(id = "com.yummly.android:id/ingredient_line")
    private ArrayList<WebElement> ingredientNames;

    public static RecipeScreen initPage() {
        return (RecipeScreen) easyInitPage(new RecipeScreen());
    }

    public RecipeScreen switchToIngredientsTab() {
        ingredientsTabButton.click();
        return this;
    }

    public RecipeScreen swipeTillReportIssueLink() {
        SwipeUtils.swipeUpTillElement(By.xpath(REPORT_ISSUE_BUTTON_XPATH));
        return this;
    }

    public RecipeScreen swipeTillEditPantryLink() {
        SwipeUtils.swipeUpTillElement(By.xpath(EDIT_PANTRY_BUTTON_XPATH));
        return this;
    }

    public RecipeScreen clickLastPlusButton() {
        plusButtons.get(plusButtons.size() - ONE).click();
        return this;
    }

    public String getLastIngredientName() {
        return ingredientNames.get(ingredientNames.size() - ONE).getText();
    }

    public void clickEditPantryLink() {
        editPantryButton.click();
    }

    public boolean isOpened() {
        return WaitUtils.isElementVisibleMediumNonFailing(recipeImage);
    }

    // The code below illustrates my attempt to implement finding of elements by image
//    public boolean isIngredientAddedToShoppingList() {
//        try {
//            return actualTest(getDriver());
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private String getReferenceImageB64(String fileName) {
//        URL refImgUrl = getClass().getClassLoader().getResource(fileName);
//        File refImgFile = null;
//        try {
//            refImgFile = Paths.get(refImgUrl.toURI()).toFile();
//            return Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
//        } catch (IOException | URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public boolean actualTest(AppiumDriver driver) throws URISyntaxException, IOException {
//        By sunriseImage = AppiumBy.image(getReferenceImageB64("minus.png"));
//        return driver.findElement(sunriseImage).isDisplayed();
//    }

}




