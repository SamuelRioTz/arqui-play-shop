package phoneapp.data;

import playshoplib.PhoneApp;

public class SimpleApp extends PhoneApp {
    private String installed;

    public SimpleApp(String name, String version, String installed) {
        super(name,version,null);
        this.installed = installed;
    }

    public String getInstalledVersion() {
        return installed;
    }

    void install(String versionToInstall) {
        installed = versionToInstall;
    }

    public boolean isInstalled() {
        return installed != null;
    }

    public boolean isUpgradeable() {
        return !installed.equals(super.getVersion());
    }
}
