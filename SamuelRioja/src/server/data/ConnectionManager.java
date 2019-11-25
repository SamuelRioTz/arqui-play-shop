package server.data;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionManager {
    private Database database;
    private DataOutputStream dataOutputStream;

    public ConnectionManager(Database database, Socket socket) throws IOException {
        this.database = database;
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

    private void messageParser(String input) {
        System.out.println(input);
        String[] message = input.split("->");
        switch (message[0]) {
            case "getAllApps":
                getAllApps();
                break;
            case "getAllActiveApps":
                getAllActiveApps();
                break;
            case "addApp":
                System.out.println("addApp");
                break;
            case "updateVersion":
                System.out.println("updateVersion");
                break;
        }
    }

    private void getAllApps() {
        System.out.println("getAllApps server");
        try {
            dataOutputStream.writeUTF("getAllApps->" + database.getAllApps());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getAllActiveApps() {
        System.out.println("getAllActiveApps server");
        try {
            dataOutputStream.writeUTF("getAllActiveApps->" + database.getAllActiveApps());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addApp() {

    }

    public void updateVersion() {

    }
}
