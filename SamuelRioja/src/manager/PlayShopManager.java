package manager;

import manager.data.ConnectionManager;
import manager.data.DataManager;
import manager.screen.ScreenManager;

public class PlayShopManager {

    public PlayShopManager() {
        DataManager dataManager = new DataManager();
        new ConnectionManager(dataManager);
        dataManager.setScreenManager(new ScreenManager(dataManager));
    }
}
