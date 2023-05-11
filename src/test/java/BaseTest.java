import io.appium.java_client.AppiumDriver;
import org.example.core.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected AppiumDriver driver;

    @BeforeMethod
    public void init() {
        driver = DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
