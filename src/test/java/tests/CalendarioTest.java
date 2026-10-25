package tests;

import activities.calendario.CalendarioActivity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** Caso de prueba: crear un evento y comprobar que aparece en Calendario. */
public class CalendarioTest extends TestBase {

    @Override
    protected String aplicacion() {
        return "calendario";
    }

    @Test
    public void crearEventoYVerificar() {
        String titulo = "Reunión familiar";
        CalendarioActivity calendario = new CalendarioActivity();
        calendario.crearEvento(titulo);

        Assertions.assertTrue(calendario.eventoVisible(titulo),
                "No se encontro el evento '" + titulo + "' en Calendario");
    }
}
