package activities.whendo;

import control.Button;
import control.Label;
import org.openqa.selenium.By;

public class MainActivity {
    public Button addButton = new Button(By.id("com.vrproductiveapps.whendo:id/fab"));
    public Label firstItemLabel = new Label(By.id("com.vrproductiveapps.whendo:id/home_list_item_text"));
}
