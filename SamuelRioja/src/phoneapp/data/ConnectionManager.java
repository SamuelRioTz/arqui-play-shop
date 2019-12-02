package phoneapp.data;

import playshoplib.ConnectionClientManager;

public class ConnectionManager extends ConnectionClientManager {
    private DataManager dataManager;


    public ConnectionManager(DataManager dataManager) {
        super("localhost",5000);
        this.dataManager = dataManager;
    }


    @Override
    public void onConnection() {
        dataManager.setConnectionManager(this);
    }

    @Override
    public void messageParser(String input) {
        String[] message = input.split("->");
        if ("getAllActiveApps".equals(message[0])) {
            dataManager.setApps(message[1]);
        }
    }

    void getAllActiveApps() {
        request("getAllActiveApps");
    }
}
