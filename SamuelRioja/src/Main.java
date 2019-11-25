import manager.PlayShopManager;
import phoneapp.PlayShopPhoneApp;
import server.PlayShopServer;

public class Main {

    public static void main(String[] args) {
        new PlayShopServer();
        new PlayShopManager();
        new PlayShopPhoneApp();
    }
}
