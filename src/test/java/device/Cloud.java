package device;

import io.appium.java_client.AppiumDriver;

public class Cloud implements IDevice {
    @Override
    public AppiumDriver create() {
        // El ejemplo cloud del docente requiere credenciales y app subida a BrowserStack.
        // No configuramos credenciales para no alterar el trabajo local.
        throw new UnsupportedOperationException("Cloud pendiente de credenciales de BrowserStack");
    }
}
