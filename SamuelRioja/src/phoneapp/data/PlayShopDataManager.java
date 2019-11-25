package phoneapp.data;

import java.util.HashMap;
import java.util.Map;

import phoneapp.screen.PlayShopScreenContainer;
import phoneapp.screen.PlayStoreAppScreen;
import phoneapp.screen.ScreenContainer;

public class PlayShopDataManager implements PhoneApp {
    private String name = "Play Store";
    private PlayShopScreenContainer playShopPhoneApp;
    private String version = "v1.0.0";
    private Map<String, PhoneApp> apps = new HashMap<>();
    private Database database = new Database();


    public PlayShopDataManager() {
        apps.put("Play Store", this);
    }

    void setApps(Map<String, PhoneApp> apps) {
        apps.put("Play Store", this);
        this.apps = apps;
        if (playShopPhoneApp != null)
            playShopPhoneApp.refresh();
    }

    public Map<String, PhoneApp> getApps() {
        return apps;
    }

    public void setPlayShopPhoneApp(PlayShopScreenContainer playShopPhoneApp) {
        this.playShopPhoneApp = playShopPhoneApp;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String getInstalledVersion() {
        return version;
    }

    @Override
    public void setVersion(String version) {
    }

    @Override
    public boolean isInstalled() {
        return true;
    }

    @Override
    public boolean isNewVersion() {
        return false;
    }

    @Override
    public void install() {
    }

    @Override
    public void uninstall() {

    }

    public void installApp(PhoneApp phoneApp) {
        database.installApp(phoneApp);
        phoneApp.install();
    }

    public void uninstallApp(PhoneApp phoneApp) {
        database.unInstallApp(phoneApp);
        phoneApp.uninstall();
    }

    public void close() {
        database.saveApps();
        playShopPhoneApp.back();
    }

    @Override
    public ScreenContainer getScreen() {
        return new PlayStoreAppScreen(this);
    }


    public void refresh() {
        if (playShopPhoneApp != null)
            playShopPhoneApp.refresh();
    }

    public void back() {
        this.playShopPhoneApp.back();
    }

    public void setScreen(ScreenContainer screen) {
        this.playShopPhoneApp.setScreen(screen);
    }
}
