package device;

import java.util.Locale;

/** Factory del docente; permite elegir el perfil de aplicacion sin duplicar Capabilities. */
public class FactoryDevice {
    public static IDevice make(String deviceType) {
        return make(deviceType, "whendo");
    }

    public static IDevice make(String deviceType, String aplicacion) {
        switch (deviceType.toLowerCase(Locale.ROOT)) {
            case "android":
                return new Android(aplicacion);
            case "ios":
                return new Ios();
            case "cloud":
                return new Cloud();
            default:
                return new Android(aplicacion);
        }
    }
}
