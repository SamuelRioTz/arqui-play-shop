package manager.data;

import playshoplib.ConnectionClientManager;

public class ConnectionManager extends ConnectionClientManager {
    private DataManager dataManager;

    public ConnectionManager(DataManager dataManager) {
        super("localhost", 5000);
        this.dataManager = dataManager;
    }

    @Override
    public void onConnection() {
        dataManager.setConnectionManager(this);
    }

    @Override
    public void messageParser(String input) {
        String[] message = input.split("->");
        if ("getAllApps".equals(message[0])) {
            dataManager.setApps(message[1]);
        }
    }

    void getAllApps() {
        request("getAllApps");
    }

    void addApp(String input) {
        request("addApp->" + input);
    }

    void upgradeVersion(String input) {
        request("upgradeVersion->" + input);
    }

    void deactivateApp(String input) {
        request("deactivateApp->" + input);
    }

    void searchApp(String text) {
        request("searchApp->" + text);
    }
}
