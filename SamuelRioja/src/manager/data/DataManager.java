package manager.data;

import manager.screen.PlayShopScreenContainer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataManager {

    private Map<String, PhoneApp> apps = new HashMap<>();

    private PlayShopScreenContainer playShopScreenContainer;

    public void setPlayShopScreenContainer(PlayShopScreenContainer playShopScreenContainer) {
        this.playShopScreenContainer = playShopScreenContainer;
    }

    public Collection<PhoneApp> getApps() {
        return apps.values();
    }

    public void updateVersion(PhoneApp serverApp) {
//        TODO: serverApp
    }

    void setApps(Map<String, PhoneApp> apps) {
        this.apps = apps;
        if (playShopScreenContainer != null) {
            playShopScreenContainer.refresh();
        }
    }
}
