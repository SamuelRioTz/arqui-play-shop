package phoneapp.data;

import playshoplib.PhoneApp;

public class SimpleApp extends PhoneApp {
    private double installed;

    public SimpleApp(String name, Double version, boolean state) {
        super(name, version, state);
    }

    public double getInstalledVersion() {
        return installed;
    }

    void install(double versionToInstall) {
        installed = versionToInstall;
    }

    public boolean isInstalled() {
        return installed != 0;
    }

    public boolean isUpgradeable() {
        return installed != super.getVersion();
    }
}
