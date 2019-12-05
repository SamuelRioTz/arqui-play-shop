package phoneapp.data;

import phoneapp.PlayShopPhoneApp;
import playshoplib.ConnectionClientManager;

public class ConnectionManager extends ConnectionClientManager {
    private PlayShopPhoneApp playShopPhoneApp;


    public ConnectionManager(PlayShopPhoneApp playShopPhoneApp) {
        super("localhost", 50000);
        this.playShopPhoneApp = playShopPhoneApp;
    }


    @Override
    public void onConnection() {
        playShopPhoneApp.setConnectionManager(this);
    }


    public void getAllActiveApps() {
        request("GET:apps", input -> playShopPhoneApp.setApps(input));
    }
}
