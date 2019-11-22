package phone.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import phone.server.model.ServerApp;

class Server {
    private List<ServerApp> apps = new LinkedList<>();
    private DataOutputStream dataOutputStream;

    List<ServerApp> getApps() {
        return apps;
    }

    Server() {
        apps.add(new ServerApp("App uno", "v1.0.0"));
        apps.add(new ServerApp("App dos", "v1.0.2"));
        apps.add(new ServerApp("App tres", "v1.0.3"));
        apps.add(new ServerApp("App cuatro", "v1.0.4"));
        new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(5000);
                Socket socket = server.accept();
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                loadData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void sendData(String message) {
        try {
            dataOutputStream.writeUTF(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        StringBuilder appsAstring = new StringBuilder("loadData->");
        for (ServerApp app : getApps()) {
            appsAstring.append(app).append(":");
        }
        sendData(appsAstring.toString());
    }

    void updateVersion(ServerApp serverApp) {
        sendData("updateVersion->" + serverApp);
    }
}
