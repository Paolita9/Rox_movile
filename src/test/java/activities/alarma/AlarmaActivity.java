package activities.alarma;

import control.Button;
import control.Label;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sesion.Sesion;

import java.time.Duration;

/** Page Object: localizadores y acciones de Google Clock / Alarma. */
public class AlarmaActivity {
    private final By locatorPestana = AppiumBy.accessibilityId("Alarm");
    private final By locatorAgregar = AppiumBy.accessibilityId("Add alarm");
    private final By locatorDiez = AppiumBy.accessibilityId("10 o'clock");
    private final By locatorAM = By.xpath("//android.widget.CompoundButton[@text='AM']");
    private final By locatorOK = By.xpath("//android.widget.Button[@text='OK']");
    private final By locatorAlarmaCreada = By.xpath(
            "//*[contains(@text,'10:00') or contains(@content-desc,'10:00')]");

    // Reutilizacion real de los controles creados por el docente.
    private final Button pestanaAlarma = new Button(locatorPestana);
    private final Button botonAgregar = new Button(locatorAgregar);
    private final Button horaDiez = new Button(locatorDiez);
    private final Button botonAM = new Button(locatorAM);
    private final Button botonOK = new Button(locatorOK);
    private final Label alarmaCreada = new Label(locatorAlarmaCreada);

    private final WebDriverWait wait = new WebDriverWait(
            Sesion.getSesion().getDevice(), Duration.ofSeconds(40));

    private void esperarYPulsar(By locator, Button boton) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        boton.click();
    }

    public void abrirPestanaAlarma() {
        esperarYPulsar(locatorPestana, pestanaAlarma);
    }

    public void agregarAlarma() {
        esperarYPulsar(locatorAgregar, botonAgregar);
    }

    public void seleccionarDiezAM() {
        esperarYPulsar(locatorDiez, horaDiez);
        esperarYPulsar(locatorAM, botonAM);
        // En el reloj inspeccionado, el selector abre los minutos en 00.
    }

    public void guardarAlarma() {
        esperarYPulsar(locatorOK, botonOK);
    }

    public boolean seMuestraAlarmaDiezAM() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorAlarmaCreada));
        return alarmaCreada.isControlDisplayed();
    }
}
