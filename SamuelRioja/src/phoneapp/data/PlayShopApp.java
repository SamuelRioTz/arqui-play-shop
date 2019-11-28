package phoneapp.data;

import java.util.HashMap;
import java.util.Map;

import phoneapp.screen.ScreenManager;
import phoneapp.screen.PlayShopAppScreen;
import phoneapp.screen.ScreenContainer;

public class PlayShop implements PhoneApp {
    private String name = "Play Store";
    private String version = "v1.0.0";
    private Map<String, PhoneApp> apps = new HashMap<>();


    public PlayShop() {
        apps.put("Play Store", this);
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
    public ScreenContainer getScreen(DataManager dataManager) {
        return new PlayShopAppScreen(dataManager);
    }


}
