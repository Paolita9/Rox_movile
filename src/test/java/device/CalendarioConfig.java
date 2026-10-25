package device;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** Datos configurables de Calendario obtenidos mediante Appium Inspector. */
public final class CalendarioConfig {
    private static final Properties DATOS = new Properties();

    static {
        try (InputStream archivo = CalendarioConfig.class.getClassLoader()
                .getResourceAsStream("calendario.properties")) {
            if (archivo == null) {
                throw new IllegalStateException("No existe src/test/resources/calendario.properties");
            }
            DATOS.load(archivo);
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer calendario.properties", e);
        }
    }

    private CalendarioConfig() {
    }

    public static String obligatorio(String clave) {
        String valor = DATOS.getProperty(clave, "").trim();
        if (valor.isEmpty()) {
            throw new IllegalStateException("Falta '" + clave
                    + "' en src/test/resources/calendario.properties. "
                    + "Obtén el dato desde Appium Inspector antes de ejecutar CalendarioTest.");
        }
        return valor;
    }

    public static boolean existeLocalizador(String nombre) {
        return !DATOS.getProperty(nombre + ".valor", "").trim().isEmpty();
    }

    public static By localizador(String nombre) {
        String tipo = obligatorio(nombre + ".tipo").toLowerCase(java.util.Locale.ROOT);
        String valor = obligatorio(nombre + ".valor");
        switch (tipo) {
            case "id":
                return By.id(valor);
            case "accessibilityid":
                return AppiumBy.accessibilityId(valor);
            case "xpath":
                return By.xpath(valor);
            default:
                throw new IllegalArgumentException("Tipo de localizador invalido para '"
                        + nombre + "': " + tipo + ". Usa id, accessibilityId o xpath.");
        }
    }
}
