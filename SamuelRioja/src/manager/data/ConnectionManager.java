package manager.data;

import manager.PlayShopManager;
import playshoplib.ConnectionClientManager;

import javax.swing.*;

public class ConnectionManager extends ConnectionClientManager {
    private PlayShopManager playShopManager;

    public ConnectionManager(PlayShopManager playShopManager) {
        super("localhost", 50000);
        this.playShopManager = playShopManager;
    }

    @Override
    public void onConnection() {
        playShopManager.setConnectionManager(this);
    }

    public void getAllApps() {
        request("GET:apps", input -> playShopManager.setApps(input));
    }

    public void addApp(String appName) {
        String sendString = "POST:apps/" + appName;
        request(sendString, input -> showInfo(sendString + " -> " + input));
    }

    public void updateApp(String updateAppString) {
        String sendString = "PUT:apps/" + updateAppString;
        request(sendString, input -> showInfo(sendString + " -> " + input));
    }

    private void showInfo(String info) {
        JOptionPane.showMessageDialog(new JPanel(), info, "Response", JOptionPane.INFORMATION_MESSAGE);
    }
}
