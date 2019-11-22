package phone.app;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

class PlayStoreServerClient {
    private PlayStoreApp playStoreApp;

    PlayStoreServerClient(PlayStoreApp playStoreApp) {
        this.playStoreApp = playStoreApp;
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 5000);
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
        case "loadData":
            loadData(message[1]);
            break;
        case "updateVersion":
            updateVersion(message[1]);
            break;
        }
    }

    private void loadData(String appsString) {
        String[] appList = appsString.split(":");
        Map<String, PhoneApp> apps = new HashMap<>();
        apps.put("Play Store", playStoreApp);
        for (String app : appList) {
            String[] appDetail = app.split("=");
            apps.put(appDetail[0], new SimpleApp(appDetail[0], appDetail[1]));
        }
        playStoreApp.loadApps(apps);
    }

    private void updateVersion(String appString) {
        String[] appInfo = appString.split("=");
        PhoneApp focusApp = playStoreApp.getApps().get(appInfo[0]);
        focusApp.setVersion(appInfo[1]);

        playStoreApp.refresh();
    }
}
