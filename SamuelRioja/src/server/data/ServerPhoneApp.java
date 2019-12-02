package server.data;

import playshoplib.PhoneApp;

public class ServerPhoneApp extends PhoneApp {

    public ServerPhoneApp(String name, String version, String state) {
        super(name, version, state);
    }

    void deactivate() {
        setState("inactive");
    }

    boolean isUpgradeable(ServerPhoneApp newApp) {
        boolean response = false;
        boolean validateVersion = newApp.getVersion().matches("\\d+\\.\\d");//[numbers.number]
        if (validateVersion) {
            Double currentVersion = Double.parseDouble(getVersion());
            Double newVersion = Double.parseDouble(newApp.getVersion());
            response = newVersion > currentVersion;
        }
        return response;
    }

    boolean isInitialVersion() {
        return getVersion().equals("1.0");
    }
}
