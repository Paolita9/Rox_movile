package activities.calendario;

import control.Button;
import control.Label;
import control.TextBox;
import device.CalendarioConfig;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sesion.Sesion;

import java.time.Duration;

/** Pantalla de Calendario: Page Object construido con los controles reutilizables. */
public class CalendarioActivity {
    private final WebDriverWait espera = new WebDriverWait(
            Sesion.getSesion().getDevice(), Duration.ofSeconds(40));

    private AndroidDriver driverAndroid() {
        return (AndroidDriver) Sesion.getSesion().getDevice();
    }

    private void asegurarCalendarioEnPantalla() {
        String paquete = CalendarioConfig.obligatorio("appPackage");
        AndroidDriver driver = driverAndroid();
        if (!paquete.equals(driver.getCurrentPackage())) {
            driver.activateApp(paquete);
        }
    }

    private void pulsar(String nombre) {
        By localizador = CalendarioConfig.localizador(nombre);
        try {
            espera.until(ExpectedConditions.elementToBeClickable(localizador));
            new Button(localizador).click();
        } catch (TimeoutException e) {
            throw new AssertionError("Calendario: no se encontro el control '" + nombre
                    + "' (" + localizador + "). App visible: "
                    + driverAndroid().getCurrentPackage(), e);
        }
    }

    public void crearEvento(String titulo) {
        asegurarCalendarioEnPantalla();
        pulsar("crear");

        // Algunas versiones muestran un menu con la opcion Evento; otras no.
        if (CalendarioConfig.existeLocalizador("evento")) {
            pulsar("evento");
        }

        By campoTitulo = CalendarioConfig.localizador("titulo");
        espera.until(ExpectedConditions.visibilityOfElementLocated(campoTitulo));
        new TextBox(campoTitulo).setText(titulo);

        pulsar("guardar");
    }

    public boolean eventoVisible(String titulo) {
        // El titulo generado por la prueba contiene solo letras, espacios y numeros.
        By resultado = By.xpath("//*[contains(@text, '" + titulo
                + "') or contains(@content-desc, '" + titulo + "')]");
        espera.until(ExpectedConditions.visibilityOfElementLocated(resultado));
        return new Label(resultado).isControlDisplayed();
    }
}
