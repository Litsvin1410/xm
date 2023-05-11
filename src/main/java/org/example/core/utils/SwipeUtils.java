package org.example.core.utils;

import org.example.core.IConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import static org.example.core.DriverFactory.getDriver;

public class SwipeUtils implements IConstants {

    private static final double SWIPE_COEFFICIENT_BIG = 0.9;
    private static final double SWIPE_COEFFICIENT_SMALL = 0.25;
    private static final double SCREEN_OFFSET_COEFFICIENT = 0.05;
    private static final int SWIPE_DURATION = 1500;

    public static void swipeScreen(SwipeDirection direction) {

        PointerInput finger = new PointerInput(PointerInput.Kind.MOUSE, "finger");
        var screenWidth = getDriver().manage().window().getSize().width;
        var screenHeight = getDriver().manage().window().getSize().height;

        Sequence dragNDrop = new Sequence(finger, ONE);
        int startX, startY, endX, endY;
        switch (direction) {
            case SWIPE_UP:
                startX = endX = screenWidth / 2;
                startY = (int) (screenHeight * SWIPE_COEFFICIENT_BIG);
                endY = (int) (screenHeight * SWIPE_COEFFICIENT_SMALL);
                break;
            case SWIPE_LEFT:
                startX = (int) (SWIPE_COEFFICIENT_BIG * screenWidth);
                endX = (int) (SCREEN_OFFSET_COEFFICIENT * screenWidth);
                startY = endY = screenHeight / 2;
                break;
            default:
                throw new UnsupportedOperationException("Unsupported swipe direction: " + direction);
        }
        dragNDrop.addAction(finger.createPointerMove(Duration.ofSeconds(ZERO), PointerInput.Origin.viewport(), startX, startY));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(SWIPE_DURATION), PointerInput.Origin.viewport(), endX, endY));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getDriver().perform(List.of(dragNDrop));
    }

    public static void swipeUpTillElement(By by) {
        LocalTime endTime = LocalTime.now().plusSeconds(SIXTY);
        while (LocalTime.now().isBefore(endTime)) {
            if (getDriver().findElements(by).size() != ZERO) break;
            swipeScreen(SwipeDirection.SWIPE_UP);
        }
    }

    public enum SwipeDirection {
        SWIPE_LEFT,
        SWIPE_UP
    }
}