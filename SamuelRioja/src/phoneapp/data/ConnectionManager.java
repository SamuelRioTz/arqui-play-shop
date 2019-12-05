package phoneapp.data;

import playshoplib.ConnectionClientManager;

public class ConnectionManager extends ConnectionClientManager {
    private DataManager dataManager;


    public ConnectionManager(DataManager dataManager) {
        super("localhost", 50000);
        this.dataManager = dataManager;
    }


    @Override
    public void onConnection() {
        dataManager.setConnectionManager(this);
    }


    void getAllActiveApps() {
        request("GET:apps", input -> dataManager.setApps(input));
    }
}
