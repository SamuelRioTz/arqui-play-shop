package phoneapp.data;

public class SimpleApp {
    private String name;
    private String version;
    private String installed;

    public SimpleApp(String name, String version, String installed) {
        this.name = name;
        this.version = version;
        this.installed = installed;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getInstalledVersion() {
        return installed;
    }

    void setVersion(String version) {
        this.version = version;
    }

    void install(String versionToInstall) {
        installed = versionToInstall;
    }

    public boolean isInstalled() {
        return installed != null;
    }

    public boolean isUpgradeable() {
        return !installed.equals(version);
    }
}
