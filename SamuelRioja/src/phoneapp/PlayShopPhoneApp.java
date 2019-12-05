package phoneapp;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import phoneapp.data.ConnectionManager;
import phoneapp.data.PlayShopApp;
import phoneapp.data.SimpleApp;
import phoneapp.screen.ScreenContainer;
import phoneapp.screen.ScreenManager;
import playshoplib.FileUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayShopPhoneApp {
    private ConnectionManager connectionManager;
    private PlayShopApp playShopApp = new PlayShopApp();
    private Gson gson = new Gson();
    private FileUtil fileUtil = new FileUtil("./database.phone.txt");
    private ScreenManager playShopPhoneApp;
    private Map<String, SimpleApp> apps;

    PlayShopPhoneApp() {
        apps = gson.fromJson(fileUtil.read(), new TypeToken<Map<String, SimpleApp>>() {
        }.getType());
        new ConnectionManager(this);
        setPlayShopPhoneApp(new ScreenManager(this));
    }

    private void setPlayShopPhoneApp(ScreenManager playShopPhoneApp) {
        this.playShopPhoneApp = playShopPhoneApp;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        connectionManager.getAllActiveApps();
    }

    private void saveCurrentState() {
        Map<String, SimpleApp> toSave = new HashMap<>();
        for (SimpleApp app : apps.values()) {
            if (app.isInstalled())
                toSave.put(app.getName(), app);
        }
        fileUtil.write(gson.toJson(toSave));
    }

    public void installApp(SimpleApp app) {
        app.install(app.getVersion());
        saveCurrentState();
        refresh();
    }

    public void unInstallApp(SimpleApp app) {
        app.install(0);
        saveCurrentState();
        refresh();
    }

    public void setApps(String input) {
        List<SimpleApp> inputApps = gson.fromJson(input, new TypeToken<List<SimpleApp>>() {
        }.getType());
        Map<String, SimpleApp> refreshApps = new HashMap<>();
        for (SimpleApp app : inputApps) {
            if (app.getState())
                refreshApps.put(app.getName(), app);
        }
        for (SimpleApp app : apps.values()) {
            SimpleApp currentInstalled = refreshApps.get(app.getName());
            if (currentInstalled == null) {
                if (app.isInstalled()) {
                    SimpleApp simpleApp = new SimpleApp(app.getName(), app.getVersion(), app.getState());
                    simpleApp.install(simpleApp.getVersion());
                    refreshApps.put(app.getName(), simpleApp);
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
