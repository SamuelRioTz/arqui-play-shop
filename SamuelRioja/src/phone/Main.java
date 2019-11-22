package phone;

import phone.app.PlayStoreApp;
import phone.screen.Phone;
import phone.server.PlayStoreServer;

public class Main {

    public static void main(String[] args) {
        new PlayStoreServer();
        PlayStoreApp playStoreApp = new PlayStoreApp();
        new Phone(playStoreApp);
    }
}
