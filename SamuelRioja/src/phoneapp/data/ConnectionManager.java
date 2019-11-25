package phoneapp.data;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ConnectionManager {
    private PlayShopDataManager playShopDataManager;
    private DataOutputStream dataOutputStream;

    public ConnectionManager(PlayShopDataManager playShopDataManager) {
        this.playShopDataManager = playShopDataManager;
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 5000);
                this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF("getAllActiveApps");
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                String message = "";
                while (!message.equals("exit")) {
                    try {
                        message = dataInputStream.readUTF();
                        messageParser(message);
                    } catch (IOException i) {
                        i.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void messageParser(String input) {
        String[] message = input.split("->");
        switch (message[0]) {
            case "getAllActiveApps":
                getAllActiveApps(message[1]);
                break;
            case "updateVersion":
//                updateVersion(message[1]);
                break;
        }
    }

    private void getAllActiveApps(String input) {
        String[] tempApps = input.split(";");
        Map<String, PhoneApp> apps = new HashMap<>();
        for (String tempApp : tempApps) {
            String[] appSplit = tempApp.split(":");
            if (appSplit.length == 3) {
                PhoneApp phoneApp = new SimpleApp(appSplit[0], appSplit[1]);
                apps.put(phoneApp.getName(), phoneApp);
            }
        }
        playShopDataManager.setApps(apps);
    }
}
