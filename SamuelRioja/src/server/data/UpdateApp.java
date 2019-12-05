package server.data;

import playshoplib.PhoneApp;

public class UpdateApp {
    private Double version;
    private boolean state;

    PhoneApp updateApp(PhoneApp appToUpdate) {
        PhoneApp response = null;
        if (appToUpdate.getVersion() < version) {
            response = new PhoneApp(appToUpdate.getName(), version, appToUpdate.getState());
        } else if (appToUpdate.getState() != state) {
            response = new PhoneApp(appToUpdate.getName(), appToUpdate.getVersion(), state);
        }
        return response;
    }
}
