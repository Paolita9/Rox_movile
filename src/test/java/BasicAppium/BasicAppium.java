package BasicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.net.MalformedURLException;

public class BasicAppium {

        private AppiumDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:deviceName", "Pixel 10 ROXI");
        capabilities.setCapability("appium:udid", "emulator-5554");
        capabilities.setCapability("appium:platformVersion", "17.0");
        capabilities.setCapability("appium:appPackage", "com.google.android.deskclock");
        capabilities.setCapability("appium:appActivity", "com.android.deskclock.DeskClock");
        capabilities.setCapability("appium:uiautomator2ServerInstallTimeout", 120000);
        capabilities.setCapability("appium:uiautomator2ServerLaunchTimeout", 120000);
        capabilities.setCapability("appium:adbExecTimeout", 120000);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),capabilities);
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

    }


    @Test
    public void test() throws InterruptedException {
        Thread.sleep(5000);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
