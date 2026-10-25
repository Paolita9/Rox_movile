package control;

import org.openqa.selenium.By;

public class Label extends BaseControl {
    public Label(By locator) {
        super(locator);
    }

    public void setText(String text) {
        findControl();
        control.sendKeys(text);
    }
}
