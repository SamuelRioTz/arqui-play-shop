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

    private void sendToClient(String data) {
        try {
            dataOutputStream.writeUTF(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void messageParser(String input) {
        log(input);
        if (input.contains("GET:apps")) {
            getAllApps();
        } else if (input.contains("GET:apps/")) {
            getApp(input.replace("GET:apps/", ""));
        } else if (input.contains("POST:apps/")) {
            addApp(input.replace("POST:apps/", ""));
        } else if (input.contains("PUT:apps/")) {
            updateApp(input.replace("PUT:apps/", ""));
        }
    }

    private void getAllApps() {
        sendToClient(dataManager.getAllApps());
    }

    private void getApp(String appName) {
        sendToClient(dataManager.getApp(appName));
    }

    private void addApp(String input) {
        if (dataManager.addApp(input)) {
            sendToClient("200");
        } else {
            sendToClient("204");
        }
    }

    private void updateApp(String input) {
        if (dataManager.updateApp(input)) {
            sendToClient("200");
        } else {
            sendToClient("404");
        }
    }


    private void log(String text) {
        System.out.println(text);
    }
}
