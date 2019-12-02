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
        log("Client connected");
        this.dataManager = dataManager;
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        String message = "";
        try {
            while (!message.equals("exit")) {
                message = dataInputStream.readUTF();
                messageParser(message);
            }
        } catch (IOException i) {
            log("Client disconnected");
//            i.printStackTrace();
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
        log(input);
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
            case "searchApp":
                searchApp(message[1]);
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

    private void searchApp(String input) {
        request("getAllApps->" + dataManager.searchApp(input));
    }

    private void log(String text) {
        System.out.println(text);
    }
}
