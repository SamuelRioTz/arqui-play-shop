package phone.app;

import java.util.HashMap;
import java.util.Map;

import phone.screen.Phone;
import phone.screen.PlayStoreAppScreen;
import phone.screen.ScreenContainer;

public class PlayStoreApp implements PhoneApp {
    private String name;
    private Phone phone;
    private String version = "v1.0.0";
    private Map<String, PhoneApp> apps = new HashMap<>();

    public PlayStoreApp() {
        name = "Play Store";
        new PlayStoreServerClient(this);
        apps.put("Play Store", this);
    }

    public Map<String, PhoneApp> getApps() {
        return apps;
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
        phoneApp.install();
    }

    public void uninstallApp(PhoneApp phoneApp) {
        phoneApp.uninstall();
    }

    @Override
    public void close() {
        phone.back();
    }

    @Override
    public ScreenContainer getScreen(Phone phone) {
        this.phone = phone;
        return new PlayStoreAppScreen(this);
    }

    void loadApps(Map<String, PhoneApp> apps) {
        this.apps = apps;
    }

    public void refresh() {
        if (phone != null)
            phone.refresh();
    }
}
