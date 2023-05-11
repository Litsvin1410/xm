package org.example.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.example.core.utils.WaitDuration;
import org.example.core.utils.mobilenativeutils.AndroidAdbUtils;
import org.example.core.utils.mobilenativeutils.MobileUtils;
import org.openqa.selenium.support.PageFactory;

import static org.example.core.DriverFactory.getDriver;

public abstract class BaseScreen {

    protected MobileUtils mobileUtils;

    public static BaseScreen easyInitPage(BaseScreen page) {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver(), WaitDuration.SHORT.getDuration()), page);
        return page;
    }

    public MobileUtils getMobileUtils() {
        if (getDriver() instanceof AndroidDriver) {
            if (mobileUtils == null) {
                mobileUtils = new AndroidAdbUtils();
            } else {
                throw new UnsupportedOperationException("Android implementation only");
            }
        }
        return mobileUtils;
    }
}
