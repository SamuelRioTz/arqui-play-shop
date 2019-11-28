package phoneapp;

import phoneapp.data.ConnectionManager;
import phoneapp.data.DataManager;
import phoneapp.screen.ScreenManager;

public class PlayShopPhoneApp {
    public PlayShopPhoneApp() {
        DataManager dataManager = new DataManager();
        new ConnectionManager(dataManager);
        dataManager.setPlayShopPhoneApp(new ScreenManager(dataManager));
    }
}
