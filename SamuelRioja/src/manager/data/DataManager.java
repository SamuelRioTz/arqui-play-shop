package manager.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import manager.screen.ScreenManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataManager {

    private Map<String, ManagerPhoneApp> apps = new HashMap<>();
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

    public Collection<ManagerPhoneApp> getApps() {
        return apps.values();
    }


    void setApps(String input) {
        this.apps = gson.fromJson(input, new TypeToken<Map<String, ManagerPhoneApp>>() {
        }.getType());
        if (screenManager != null) {
            screenManager.refresh();
        }
    }

    public void addApp(String name) {
        ManagerPhoneApp newApp = new ManagerPhoneApp(name, "1.0", "active");
        if (connectionManager != null) connectionManager.addApp(gson.toJson(newApp));
    }

    public void upgradeVersion(ManagerPhoneApp managerPhoneApp) {
        if (connectionManager != null) connectionManager.upgradeVersion(gson.toJson(managerPhoneApp));
    }

    public void deactivateApp(ManagerPhoneApp managerPhoneApp) {
        if (connectionManager != null) connectionManager.deactivateApp(gson.toJson(managerPhoneApp));
    }

    public void searchApp(String text) {
        if (connectionManager != null) {
            if (text.equals("")) connectionManager.getAllApps();
            else connectionManager.searchApp(text);
        }
    }
}
