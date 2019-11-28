package server.data;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionManager {
    private DataManager dataManager;
    private DataOutputStream dataOutputStream;

    public ConnectionManager(DataManager dataManager, Socket socket) throws IOException {
        this.dataManager = dataManager;
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
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
    }

    private void request(String data) {
        try {
            dataOutputStream.writeUTF(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void messageParser(String input) {
        String[] message = input.split("->");
        switch (message[0]) {
            case "getAllApps":
                getAllApps();
                break;
            case "getAllActiveApps":
                getAllActiveApps();
                break;
            case "addApp":
                addApp(message[1]);
                break;
            case "upgradeVersion":
                upgradeVersion(message[1]);
                break;
            case "deactivateApp":
                deactivateApp(message[1]);
                break;
        }
    }

    private void getAllApps() {
        request("getAllApps->" + dataManager.getAllApps());
    }

    private void getAllActiveApps() {
        request("getAllActiveApps->" + dataManager.getAllActiveApps());
    }

    private void addApp(String input) {
        dataManager.addApp(input);
        getAllApps();
    }

    private void upgradeVersion(String input) {
        dataManager.updateVersion(input);
        getAllApps();
    }

    private void deactivateApp(String input) {
        dataManager.deactivateApp(input);
        getAllApps();
    }
}
