package phoneapp.data;

import phoneapp.screen.ScreenContainer;

public interface PhoneApp {

    String getName();

    String getVersion();

    String getInstalledVersion();

    void setVersion(String version);

    boolean isInstalled();

    boolean isNewVersion();

    void install();

    void uninstall();

    ScreenContainer getScreen();

}
