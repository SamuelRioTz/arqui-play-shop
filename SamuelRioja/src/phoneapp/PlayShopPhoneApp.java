package phoneapp;

import phoneapp.data.ConnectionManager;
import phoneapp.data.PlayShopDataManager;
import phoneapp.screen.PlayShopScreenContainer;

public class PlayShopPhoneApp {
    public PlayShopPhoneApp() {
        PlayShopDataManager playShopDataManager = new PlayShopDataManager();
         new ConnectionManager(playShopDataManager);
        PlayShopScreenContainer playShopScreenContainer = new PlayShopScreenContainer(playShopDataManager);
        playShopDataManager.setPlayShopPhoneApp(playShopScreenContainer);
    }
}
