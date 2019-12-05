package manager.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import manager.screen.ScreenManager;
import playshoplib.PhoneApp;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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

    public void downloadApps() {
        if (connectionManager != null) {
            connectionManager.getAllApps();
        }
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
        List<PhoneApp> inputApps = gson.fromJson(input, new TypeToken<List<PhoneApp>>() {
        }.getType());
        apps = new HashMap<>();
        for (PhoneApp app : inputApps) {
            apps.put(app.getName(), app);
        }
        if (screenManager != null) {
            screenManager.refresh();
        }
    }

    public void addApp(String name) {
        if (connectionManager != null) connectionManager.addApp(name);
    }

    public void upgradeVersion(PhoneApp managerPhoneApp, double versionValue) {
        UpdateAppRequest updateApp = new UpdateAppRequest(versionValue, managerPhoneApp.getState());
        if (connectionManager != null)
            connectionManager.updateApp(managerPhoneApp.getName() + "/" + gson.toJson(updateApp));
    }

    public void deactivateApp(PhoneApp managerPhoneApp) {
        UpdateAppRequest updateApp = new UpdateAppRequest(managerPhoneApp.getVersion(), false);
        if (connectionManager != null)
            connectionManager.updateApp(managerPhoneApp.getName() + "/" + gson.toJson(updateApp));
    }

    public void searchApp(String text) {
//        if (connectionManager != null) {
//            if (text.equals("")) connectionManager.getAllApps();
//            else connectionManager.searchApp(text);
//        }
    }
}
