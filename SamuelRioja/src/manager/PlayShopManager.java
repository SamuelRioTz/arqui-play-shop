package manager;

import manager.data.ConnectionManager;
import manager.data.DataManager;
import manager.screen.PlayShopScreenContainer;

public class PlayShopManager {

    public PlayShopManager() {
        DataManager dataManager = new DataManager();
         new ConnectionManager(dataManager);
        PlayShopScreenContainer playShopScreenContainer = new PlayShopScreenContainer(dataManager);
        dataManager.setPlayShopScreenContainer(playShopScreenContainer);
    }
}
