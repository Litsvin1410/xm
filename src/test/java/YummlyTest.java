import org.example.pages.*;
import org.example.pages.menues.BottomMenu;
import org.example.pages.menues.PSPTabBar;
import org.example.pages.popups.ConnectToSocialNetworkPopUp;
import org.example.steps.CloseToolTipsSteps;
import org.testng.annotations.Test;

import static org.example.pages.menues.BottomMenu.BottomMenuItem.SEARCH;
import static org.testng.Assert.*;

public class YummlyTest extends BaseTest {

    private static final String RECIPE_NAME = "tiramisu";
    private static final String EXPECTED_ADDED_FOOD_AMOUNT = "1";

    // Notes:
    // I tried to perform some actions with AppiumBy.image with usage of openCV. But it gives unreliable results.
    // Probably it requires further tuning or so. This is why i:
    //       1. used workaround when closing educational tooltip for first-time-user
    //       2. decided not to check is plus icon changed to minus icon after recipe ingredient is added to shopping list
    //           because there are no attributes to tell me about the state of plus/minus icon.
    //
    // One more nuance:
    // one of the steps in test case says:
    // "Click on shopping cart icon on top of the page."
    // I didn't find such icon. So I decided to go to the shopping list in different way. Hope that's OK. In real life
    // world I would clarify the app's behavior and apply som changes to test case if needed.
    @Test
    public void yummlyTest() {

        StartScreen.initPage().clickSkipPersonalizationButton();

        NewFeaturesScreen newFeaturesScreen = NewFeaturesScreen.initPage();
        String initialFeatureDescription = newFeaturesScreen.getFeatureDescription();
        newFeaturesScreen.swipeToNextNewFeature();
        String newFeatureDescription = newFeaturesScreen.getFeatureDescription();
        assertTrue(newFeaturesScreen.isOpened(), "New features carousel isn't opened");
        assertNotEquals(initialFeatureDescription, newFeatureDescription,
                "User is not able to swipe between new feature descriptions");

        newFeaturesScreen.closeNewFeaturesDescriptionScreen();
        MainScreen.initPage().waitTillFeedIsLoaded();
        new CloseToolTipsSteps().closeToolTipsOnMainScreen();

        SearchScreen searchScreen = (SearchScreen) BottomMenu.initPage().openPageFromBottomMenu(SEARCH);
        new CloseToolTipsSteps().closeToolTipsOnSearchScreen();
        searchScreen.searchForRecipe(RECIPE_NAME);
        assertTrue(searchScreen.verifyResultsAreFound(), String.format("No results found for '%s' search request", RECIPE_NAME));

        String firstResultTitle = searchScreen.getFirstResultTitle();
        assertTrue(firstResultTitle.toLowerCase().contains(RECIPE_NAME), "Unable to find recipe");

        searchScreen.openRecipeByName(searchScreen.getFirstResultTitle());
        RecipeScreen recipeScreen = RecipeScreen.initPage();
        recipeScreen.switchToIngredientsTab()
                .swipeTillReportIssueLink();
        String expectedLastIngredientName = recipeScreen.getLastIngredientName();
        recipeScreen.clickLastPlusButton();

        // See comments above this test (the part related to AppiumBy.image)
        // boolean isIngredientAdded = recipeScreen.isIngredientAddedToShoppingList();
        // assertTrue(isIngredientAdded, "User is unable to add an ingredient to shopping cart");

        recipeScreen.swipeTillEditPantryLink()
                .clickEditPantryLink();
        ConnectToSocialNetworkPopUp.initPage().closeConnectionToSocialNetworkPopUp();

        ShopScreen shopScreen = (ShopScreen) PSPTabBar.initPage().opensScreenViaPSPTabBar(PSPTabBar.PSPTab.SHOP);
        assertEquals(EXPECTED_ADDED_FOOD_AMOUNT, shopScreen.getAmountOfFoodSnacksInList(),
                "Amount of food snack in list is calculated incorrectly");
        assertEquals(expectedLastIngredientName, shopScreen.getFirstSnackName(),
                "Incorrect ingredient is added to Snack foods list");

        recipeScreen = shopScreen.expandIngredientByName(expectedLastIngredientName)
                .clickRecipeButtonOnExpandedIngredient();

        assertTrue(recipeScreen.isOpened(),
                "Recipe screen isn't opened after navigation from Shop > Snack foods item");

        // See comments above this test (the part related to AppiumBy.image)
        // isIngredientAdded = recipeScreen.swipeTillReportIssueLink().isIngredientAddedToShoppingList();
        // assertTrue(isIngredientAdded, "Ingredient should stay selected after moving from Shop screen to recipe");
    }

}
