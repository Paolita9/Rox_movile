package sesion;

import device.FactoryDevice;
import io.appium.java_client.AppiumDriver;

/** Singleton de una sesion Appium por ejecucion; conserva getSesion() del docente. */
public class Sesion {
    private static Sesion sesion = null;
    private final AppiumDriver device;

    private Sesion(String aplicacion) {
        device = FactoryDevice.make("android", aplicacion).create();
    }

    public static synchronized Sesion getSesion() {
        // Si ya existe una sesion, la reutiliza; si no, sigue el ejemplo WhenDo.
        if (sesion == null) {
            sesion = new Sesion("whendo");
        }
        return sesion;
    }

    public static synchronized Sesion getSesion(String aplicacion) {
        if (sesion == null) {
            sesion = new Sesion(aplicacion);
        }
        return sesion;
    }

    public AppiumDriver getDevice() {
        return device;
    }

    public synchronized void closeSesion() {
        try {
            device.quit();
        } finally {
            sesion = null;
        }
    }
}
