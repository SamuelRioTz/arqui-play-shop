package manager.data;

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

    void getAllApps() {
        request("GET:apps",input -> dataManager.setApps(input));
    }

    void addApp(String appName) {
        String sendString = "POST:apps/" + appName;
        request(sendString,input -> System.out.println(sendString + " -> " + input));
    }

    void updateApp(String updateAppString) {
        String sendString = "PUT:apps/" + updateAppString;
        request(sendString,input -> System.out.println(sendString + " -> " + input));
    }

}
