package manager.data;

import playshoplib.PhoneApp;

public class ManagerPhoneApp extends PhoneApp {

    ManagerPhoneApp(String name, String version, String state) {
        super(name, version, state);
    }

    public boolean isActive() {
        return super.getState().equals("active");
    }
}
