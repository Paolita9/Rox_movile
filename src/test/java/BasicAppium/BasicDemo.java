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

public class BasicDemo {

        private AppiumDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:deviceName", "Pixel 6 Pro");
        capabilities.setCapability("appium:udid", "emulator-5554");
        capabilities.setCapability("appium:platformVersion", "15.0");
        capabilities.setCapability("appium:appPackage", "com.browserstack.demo.app");
        capabilities.setCapability("appium:appActivity", "host.exp.exponent.MainActivity");
        capabilities.setCapability("appium:uiautomator2ServerInstallTimeout", 120000);
        capabilities.setCapability("appium:uiautomator2ServerLaunchTimeout", 120000);
        capabilities.setCapability("appium:adbExecTimeout", 120000);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),capabilities);
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

    }

    @Test
    public void verifyLoginInDemoApp() throws InterruptedException {
        // click menu
        //driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu\"]")).click();
          //driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='menu']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.view.ViewGroup[@content-desc='menu']")
                )).click();
        //click sing in
        driver.findElement(AppiumBy.accessibilityId("nav-signin")).click();
        // select user
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Accepted usernames are\"]")).click();
        driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='demouser']")).click();
        //select password
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Password for all users\"]\n")).click();
        driver.findElement(By.xpath(" //android.widget.CheckedTextView[@text='testingisfun99']")).click();
        //click button login
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Sign in\"]")).click();
        //click menu
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu\"]")).click();

        // verification - logout
        Assertions.assertTrue(driver.findElement(AppiumBy.accessibilityId("nav-logout")).isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
