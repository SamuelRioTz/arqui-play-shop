package manager.data;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ConnectionManager {
    private DataManager dataManager;
    private DataOutputStream dataOutputStream;

    public ConnectionManager(DataManager dataManager) {
        this.dataManager = dataManager;
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 5000);
                this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF("getAllApps");
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
            case "getAllApps":
                getAllApps(message[1]);
                break;
            case "addApp":
//                TODO: addApp
                break;
            case "updateVersion":
//                TODO: updateVersion
                break;
        }
    }

    private void getAllApps(String input) {
        String[] tempApps = input.split(";");
        Map<String, PhoneApp> apps = new HashMap<>();
        for (String tempApp : tempApps) {
            String[] appSplit = tempApp.split(":");
            if (appSplit.length == 3) {
                PhoneApp phoneApp = new PhoneApp(appSplit[0], appSplit[1], appSplit[2]);
                apps.put(phoneApp.getName(), phoneApp);
            }
        }
        dataManager.setApps(apps);
    }

}
