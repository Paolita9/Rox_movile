package activities.whendo;

import control.Button;
import control.TextBox;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class TaskActivity {
    public TextBox titleTextBox = new TextBox(By.id("com.vrproductiveapps.whendo:id/noteTextTitle"));
    public TextBox notesTextBox = new TextBox(By.id("com.vrproductiveapps.whendo:id/noteTextNotes"));
    public Button saveButton = new Button(AppiumBy.accessibilityId("Save"));
}
