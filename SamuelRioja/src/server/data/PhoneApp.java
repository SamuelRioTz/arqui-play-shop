package server.data;

public class PhoneApp {
    private String name;
    private String version;
    private String state;

    public PhoneApp(String name, String version, String state) {
        this.name = name;
        this.version = version;
        this.state = state;
    }

    String getName() {
        return name;
    }

    String getState() {
        return state;
    }

    void deactivate() {
        this.state = "inactive";
    }

    boolean isUpgradeable(PhoneApp newApp) {
        boolean response = false;
        boolean validateVersion = newApp.version.matches("\\d+\\.\\d");//[numbers.number]
        if (validateVersion) {
            Double currentVersion = Double.parseDouble(version);
            Double newVersion = Double.parseDouble(newApp.version);
            response = newVersion > currentVersion;
        }
        return response;
    }

    boolean isInitialVersion() {
        return version.equals("1.0");
    }
}
