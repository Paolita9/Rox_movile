package tests;

import activities.whendo.MainActivity;
import activities.whendo.TaskActivity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import sesion.Sesion;

// Deshabilitada hasta instalar la app WhenDo. Asi no altera las pruebas que ya tienes.
@Disabled("Ejemplo del docente: requiere instalar WhenDo en el emulador")
public class AddTaskTest {
    MainActivity mainActivity = new MainActivity();
    TaskActivity taskActivity = new TaskActivity();

    @AfterEach
    public void tearDown() {
        Sesion.getSesion().closeSesion();
    }

    @Test
    public void addTask() {
        String title = "UCB" + System.currentTimeMillis();
        mainActivity.addButton.click();
        taskActivity.titleTextBox.setText(title);
        taskActivity.notesTextBox.setText("New Task Notes");
        taskActivity.saveButton.click();
        Assertions.assertEquals(title, mainActivity.firstItemLabel.getText());
    }
}
