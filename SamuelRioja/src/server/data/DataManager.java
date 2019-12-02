package server.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import playshoplib.Database;

import java.util.HashMap;
import java.util.Map;


public class DataManager {
    private Database database = new Database("./database.server.txt");
    private Map<String, ServerPhoneApp> apps;
    private Gson gson = new Gson();

    public DataManager() {
        apps = gson.fromJson(database.read(), new TypeToken<Map<String, ServerPhoneApp>>() {
        }.getType());
    }

    private void saveCurrentState() {
        database.write(gson.toJson(apps));
    }


    String getAllApps() {
        return gson.toJson(apps);
    }

    String getAllActiveApps() {
        Map<String, ServerPhoneApp> response = new HashMap<>();
        for (ServerPhoneApp app : apps.values()) {
            if (app.getState().equals("active")) response.put(app.getName(), app);
        }
        return gson.toJson(response);
    }

    void addApp(String input) {
        ServerPhoneApp newVersionApp = gson.fromJson(input, ServerPhoneApp.class);
        if (apps.get(newVersionApp.getName()) == null && newVersionApp.isInitialVersion()) {
            apps.put(newVersionApp.getName(), newVersionApp);
            saveCurrentState();
        }
    }

    void updateVersion(String input) {
        ServerPhoneApp newVersionApp = gson.fromJson(input, ServerPhoneApp.class);
        ServerPhoneApp toUpgrade = apps.get(newVersionApp.getName());
        if (toUpgrade.isUpgradeable(newVersionApp)) {
            apps.put(newVersionApp.getName(), newVersionApp);
            saveCurrentState();
        }
    }

    void deactivateApp(String input) {
        ServerPhoneApp serverPhoneApp = gson.fromJson(input, ServerPhoneApp.class);
        ServerPhoneApp currentApp = apps.get(serverPhoneApp.getName());
        if (currentApp != null) {
            currentApp.deactivate();
            saveCurrentState();
        }
    }

    String searchApp(String input) {
        Map<String, ServerPhoneApp> response = new HashMap<>();
        for (ServerPhoneApp app : apps.values()) {
            if (app.getName().toLowerCase().contains(input.toLowerCase()))
                response.put(app.getName(), app);
        }
        return gson.toJson(response);
    }
}
