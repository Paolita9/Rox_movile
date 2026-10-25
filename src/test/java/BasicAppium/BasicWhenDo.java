package BasicAppium;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;

public class BasicWhenDo {

        private AppiumDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:deviceName", "Pixel 6 Pro");
        capabilities.setCapability("appium:udid", "emulator-5554");
        capabilities.setCapability("appium:platformVersion", "15.0");
        capabilities.setCapability("appium:appPackage", "com.vrproductiveapps.whendo");
        capabilities.setCapability("appium:appActivity", ".ui.HomeActivity");
        capabilities.setCapability("appium:uiautomator2ServerInstallTimeout", 120000);
        capabilities.setCapability("appium:uiautomator2ServerLaunchTimeout", 120000);
        capabilities.setCapability("appium:adbExecTimeout", 120000);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void verifyLoginInDemoApp() throws InterruptedException {
        String title="UCB"+new Date().getTime();
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys(title);
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys("This is aNotes");
        driver.findElement(AppiumBy.accessibilityId("Save")).click();
        String expectedResult=title;
        String actualResult=driver.findElement(By.id("com.vrproductiveapps.whendo:id/home_list_item_text")).getText();
        Assertions.assertEquals(expectedResult,actualResult);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
