package playshoplib;

public class PhoneApp {
    private String name;
    private String version;
    private String state;

    public PhoneApp(String name, String version, String state) {
        this.name = name;
        this.version = version;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
