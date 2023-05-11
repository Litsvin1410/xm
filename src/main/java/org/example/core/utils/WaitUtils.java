package org.example.core.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.example.core.DriverFactory.getDriver;

public class WaitUtils {

    public static WebElement waitForVisibleShort(WebElement element) {
        waitForVisible(getDriver(), element, WaitDuration.SHORT);
        return element;
    }

    public static WebElement waitForVisibleMedium(WebElement element) {
        waitForVisible(getDriver(), element, WaitDuration.MEDIUM);
        return element;
    }

    public static boolean isElementVisibleMediumNonFailing(WebElement element) {
        boolean isVisible = false;
        try {
            isVisible = waitForVisible(getDriver(), element, WaitDuration.MEDIUM);
        } catch (TimeoutException e) {
            // element isn't found. will return false
        }
        return isVisible;
    }


    private static boolean waitForVisible(AppiumDriver driver, WebElement element, WaitDuration waitDuration) {
        WebDriverWait waitForVisible = new WebDriverWait(driver, waitDuration.getDuration());
        return waitForVisible.withTimeout(waitDuration.getDuration()).until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
