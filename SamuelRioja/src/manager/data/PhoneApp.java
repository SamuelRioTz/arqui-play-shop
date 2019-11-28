package manager.data;

public class PhoneApp {
    private String name;
    private String version;
    private String state;

    PhoneApp(String name, String version, String state) {
        this.name = name;
        this.version = version;
        this.state = state;
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

    public String getState() {
        return state;
    }

    public boolean isActive() {
        return state.equals("active");
    }
}
