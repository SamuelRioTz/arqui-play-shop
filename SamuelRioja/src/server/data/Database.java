package server.data;

import java.util.HashMap;
import java.util.Map;


public class Database {

    private Map<String, PhoneApp> apps = new HashMap<>();

    public Database() {
        apps.put("app uno", new PhoneApp("app uno", "1.0", "active"));
        apps.put("app dos", new PhoneApp("app dos", "2.0", "active"));
        apps.put("app tres", new PhoneApp("app tres", "3.0", "active"));
        apps.put("app cuatro", new PhoneApp("app cuatro", "4.0", "inactive"));
    }

    public void addApp(PhoneApp app) {
        apps.put(app.name, app);
    }

    public void deactivateApp(String name) {
        PhoneApp phoneApp = apps.get(name);
        if (phoneApp != null) {
            phoneApp.state = "inactivate";
        }
    }

    String getAllApps() {
        StringBuilder response = new StringBuilder();
        for (PhoneApp app : apps.values()) {
            response.append(app.toString()).append(";");
        }
        return response.toString();
    }

    String getAllActiveApps() {
        StringBuilder response = new StringBuilder();
        for (PhoneApp app : apps.values()) {
            if (app.state.equals("active"))
                response.append(app.toString()).append(";");
        }
        return response.toString();
    }
}
