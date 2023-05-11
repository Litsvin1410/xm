package org.example.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.example.core.utils.TestProperty;

import java.net.MalformedURLException;
import java.net.URL;

import static org.example.core.utils.PropertyReader.readTestProperty;

public class DriverFactory {

    private static AppiumDriver driver;

    public static AppiumDriver getDriver() {
        if (driver == null) {
            createDriver();
        }
        return driver;
    }

    private static void createDriver() {
        if (readTestProperty(TestProperty.APP_PATH).endsWith(".apk")) {
            createAndroidDriver();
        } else {
            throw new UnsupportedOperationException("Android implementation only");
        }
    }

    private static void createAndroidDriver() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid(readTestProperty(TestProperty.DEVICE_UDID))
                .setApp(readTestProperty(TestProperty.APP_PATH))
                .setAppActivity(readTestProperty(TestProperty.APP_ACTIVITY))
                .setAppWaitActivity(readTestProperty(TestProperty.APP_WAIT_ACTIVITY))
                .setAutoGrantPermissions(true);

        try {
            driver = new AndroidDriver(new URL(readTestProperty(TestProperty.URL)), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD, readTestProperty(TestProperty.IMAGE_MATCH_THRESHOLD));
        driver.setSetting(Setting.CHECK_IMAGE_ELEMENT_STALENESS, false);
    }
}
