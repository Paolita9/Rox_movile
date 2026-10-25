package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import sesion.Sesion;

/** Base reutilizable: el perfil de cada app se define en la subclase. */
public abstract class TestBase {
    private boolean sesionIniciada;

    protected abstract String aplicacion();

    @BeforeEach
    public void setUpTestBase() {
        Sesion.getSesion(aplicacion());
        sesionIniciada = true;
    }

    @AfterEach
    public void tearDownTestBase() {
        if (sesionIniciada) {
            Sesion.getSesion().closeSesion();
            sesionIniciada = false;
        }
    }
}
