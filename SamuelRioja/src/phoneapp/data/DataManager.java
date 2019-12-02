package phoneapp.data;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import phoneapp.screen.ScreenContainer;
import phoneapp.screen.ScreenManager;
import playshoplib.Database;

import java.util.HashMap;
import java.util.Map;

public class DataManager {
    private ConnectionManager connectionManager;
    private PlayShopApp playShopApp = new PlayShopApp();
    private Gson gson = new Gson();
    private Database database = new Database("./database.phone.txt");
    private ScreenManager playShopPhoneApp;
    private Map<String, SimpleApp> apps;

    public DataManager() {
        apps = gson.fromJson(database.read(), new TypeToken<Map<String, SimpleApp>>() {
        }.getType());
    }

    public void setPlayShopPhoneApp(ScreenManager playShopPhoneApp) {
        this.playShopPhoneApp = playShopPhoneApp;
    }

    void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        connectionManager.getAllActiveApps();
    }

    private void saveCurrentState() {
        Map<String, SimpleApp> toSave = new HashMap<>();
        for (SimpleApp app : apps.values()) {
            if (app.isInstalled())
                toSave.put(app.getName(), app);
        }
        database.write(gson.toJson(toSave));
    }

    public void installApp(SimpleApp app) {
        app.install(app.getVersion());
        saveCurrentState();
        refresh();
    }

    public void unInstallApp(SimpleApp app) {
        app.install(null);
        saveCurrentState();
        refresh();
    }

    void setApps(String input) {
        Map<String, SimpleApp> refreshApps = gson.fromJson(input, new TypeToken<Map<String, SimpleApp>>() {
        }.getType());
        for (SimpleApp app : apps.values()) {
            SimpleApp currentInstalled = refreshApps.get(app.getName());
            if (currentInstalled == null) {
                if (app.isInstalled()) {
                    app.setVersion(app.getInstalledVersion());
                    refreshApps.put(app.getName(), app);
                }
            } else {
                if (app.isInstalled()) {
                    currentInstalled.install(app.getInstalledVersion());
                }
            }
        }
        apps = refreshApps;
    }

    public void refresh() {
        if (playShopPhoneApp != null)
            playShopPhoneApp.refresh();
    }

    public Map<String, SimpleApp> getApps() {
        return apps;
    }

    public void close() {
        back();
    }

    public void setScreen(ScreenContainer screen) {
        if (connectionManager != null)
            connectionManager.getAllActiveApps();
        playShopPhoneApp.setScreen(screen);
    }

    public void back() {
        playShopPhoneApp.back();
    }

    public PlayShopApp getPlayShopApp() {
        return playShopApp;
    }
}
