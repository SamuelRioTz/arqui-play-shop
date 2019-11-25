package server.data;

public class PhoneApp {
    String name;
    String version;
    String state;

    public PhoneApp(String name, String version, String state) {
        this.name = name;
        this.version = version;
        this.state = state;
    }

    @Override
    public String toString() {
        return name + ":" + version + ":" + state;
    }
}
