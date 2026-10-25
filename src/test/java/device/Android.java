package device;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/** Configuracion local Android; mantiene WhenDo como perfil predeterminado del docente. */
public class Android implements IDevice {
    private final String aplicacion;

    public Android() {
        this("whendo");
    }

    public Android(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    @Override
    public AndroidDriver create() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:deviceName", "Pixel 6 Pro");
        capabilities.setCapability("appium:udid", "emulator-5554");
        capabilities.setCapability("appium:platformVersion", "15.0");

        switch (aplicacion.toLowerCase(java.util.Locale.ROOT)) {
            case "alarma":
                capabilities.setCapability("appium:appPackage", "com.google.android.deskclock");
                capabilities.setCapability("appium:appActivity", "com.android.deskclock.DeskClock");
                break;
            case "calendario":
                capabilities.setCapability("appium:appPackage", CalendarioConfig.obligatorio("appPackage"));
                capabilities.setCapability("appium:appActivity", CalendarioConfig.obligatorio("appActivity"));
                // Conserva la configuracion inicial y la cuenta de Google Calendar.
                capabilities.setCapability("appium:noReset", true);
                break;
            case "whendo":
                capabilities.setCapability("appium:appPackage", "com.vrproductiveapps.whendo");
                capabilities.setCapability("appium:appActivity", ".ui.HomeActivity");
                break;
            default:
                throw new IllegalArgumentException("Perfil de aplicacion no configurado: " + aplicacion);
        }

        capabilities.setCapability("appium:uiautomator2ServerInstallTimeout", 120000);
        capabilities.setCapability("appium:uiautomator2ServerLaunchTimeout", 120000);
        capabilities.setCapability("appium:adbExecTimeout", 120000);

        try {
            AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            return driver;
        } catch (MalformedURLException e) {
            throw new IllegalStateException("URL de Appium invalida", e);
        }
    }
}
