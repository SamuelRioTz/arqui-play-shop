package playshoplib;

public class PhoneApp {
    private String name;
    private Double version;
    private boolean state;

    public PhoneApp(String name, Double version, boolean state) {
        this.name = name;
        this.version = version;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public Double getVersion() {
        return version;
    }

    public boolean getState() {
        return state;
    }

}
