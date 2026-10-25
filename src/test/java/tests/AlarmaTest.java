package tests;

import activities.alarma.AlarmaActivity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** Prueba JUnit: crear una alarma de 10:00 AM y verificar que se muestre. */
public class AlarmaTest extends TestBase {

    @Override
    protected String aplicacion() {
        return "alarma";
    }

    @Test
    public void crearAlarmaDiezAM() {
        AlarmaActivity alarma = new AlarmaActivity();
        alarma.abrirPestanaAlarma();
        alarma.agregarAlarma();
        alarma.seleccionarDiezAM();
        alarma.guardarAlarma();

        Assertions.assertTrue(alarma.seMuestraAlarmaDiezAM(),
                "No se encuentra la alarma de las 10:00 AM en Reloj");
    }
}
