package control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sesion.Sesion;

public class BaseControl {
    protected WebElement control;
    protected By locator;

    public BaseControl(By locator) {
        this.locator = locator;
    }

    public void findControl() {
        control = Sesion.getSesion().getDevice().findElement(locator);
    }

    public void click() {
        findControl();
        control.click();
    }

    public String getText() {
        findControl();
        return control.getText();
    }

    public boolean isControlDisplayed() {
        findControl();
        return control.isDisplayed();
    }
}
