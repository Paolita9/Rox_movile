package device;

import io.appium.java_client.AppiumDriver;

public class Ios implements IDevice {
    @Override
    public AppiumDriver create() {
        throw new UnsupportedOperationException("iOS aun no fue configurado por el docente");
    }
}
