package phone.app;

import phone.screen.Phone;
import phone.screen.ScreenContainer;

public interface PhoneApp {

    String getName();

    String getVersion();

    String getInstalledVersion();

    void setVersion(String version);

    boolean isInstalled();

    boolean isNewVersion();

    void install();

    void uninstall();

    void close();

    ScreenContainer getScreen(Phone phone);

}
