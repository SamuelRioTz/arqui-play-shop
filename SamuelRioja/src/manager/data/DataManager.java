package manager.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import manager.screen.ScreenManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataManager {

    private Map<String, PhoneApp> apps = new HashMap<>();
    private ConnectionManager connectionManager;
    private ScreenManager screenManager;

    private Gson gson = new Gson();

    void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        connectionManager.getAllApps();
    }

    public void setScreenManager(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public void back() {
        this.screenManager.back();
    }

    public Collection<PhoneApp> getApps() {
        return apps.values();
    }


    void setApps(String input) {
        this.apps = gson.fromJson(input, new TypeToken<Map<String, PhoneApp>>() {
        }.getType());
        if (screenManager != null) {
            screenManager.refresh();
        }
    }

    public void addApp(String name) {
        PhoneApp newApp = new PhoneApp(name, "1.0", "active");
        if (connectionManager != null) connectionManager.addApp(gson.toJson(newApp));
    }

    public void upgradeVersion(PhoneApp phoneApp) {
        if (connectionManager != null) connectionManager.upgradeVersion(gson.toJson(phoneApp));
    }

    public void deactivateApp(PhoneApp phoneApp) {
        if (connectionManager != null) connectionManager.deactivateApp(gson.toJson(phoneApp));
    }

    public void searchApp(String text) {
        if (connectionManager != null) {
            if (text.equals("")) connectionManager.getAllApps();
            else connectionManager.searchApp(text);
        }
    }
}
