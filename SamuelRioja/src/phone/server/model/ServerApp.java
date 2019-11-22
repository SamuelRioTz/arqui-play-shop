package phone.server.model;

public class ServerApp {
    private String name;
    private String version;

    public ServerApp(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return name + "=" + version;
    }

    void updateVersion(String newVersion) {
        version = newVersion;
    }
}
