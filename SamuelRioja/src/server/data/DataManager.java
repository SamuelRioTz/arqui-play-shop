package server.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;


public class DataManager {
    private Database database = new Database();
    private Map<String, PhoneApp> apps;
    private Gson gson = new Gson();

    public DataManager() {
        apps = gson.fromJson(database.read(), new TypeToken<Map<String, PhoneApp>>() {
        }.getType());
    }

    private void saveCurrentState() {
        database.write(gson.toJson(apps));
    }


    String getAllApps() {
        return gson.toJson(apps);
    }

    String getAllActiveApps() {
        Map<String, PhoneApp> response = new HashMap<>();
        for (PhoneApp app : apps.values()) {
            if (app.getState().equals("active")) response.put(app.getName(), app);
        }
        return gson.toJson(response);
    }

    void addApp(String input) {
        PhoneApp newVersionApp = gson.fromJson(input, PhoneApp.class);
        if (apps.get(newVersionApp.getName()) == null && newVersionApp.isInitialVersion()) {
            apps.put(newVersionApp.getName(), newVersionApp);
            saveCurrentState();
        }
    }

    void updateVersion(String input) {
        PhoneApp newVersionApp = gson.fromJson(input, PhoneApp.class);
        PhoneApp toUpgrade = apps.get(newVersionApp.getName());
        if (toUpgrade.isUpgradeable(newVersionApp)) {
            apps.put(newVersionApp.getName(), newVersionApp);
            saveCurrentState();
        }
    }

    void deactivateApp(String input) {
        PhoneApp phoneApp = gson.fromJson(input, PhoneApp.class);
        PhoneApp currentApp = apps.get(phoneApp.getName());
        if (currentApp != null) {
            currentApp.deactivate();
            saveCurrentState();
        }
    }

    String searchApp(String input) {
        Map<String, PhoneApp> response = new HashMap<>();
        for (PhoneApp app : apps.values()) {
            if (app.getName().toLowerCase().contains(input.toLowerCase()))
                response.put(app.getName(), app);
        }
        return gson.toJson(response);
    }
}
