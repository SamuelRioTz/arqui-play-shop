package manager.data;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionManager {
    private DataManager dataManager;
    private DataOutputStream dataOutputStream;
    private String host = "127.0.0.1";
    private int port = 5000;

    public ConnectionManager(DataManager dataManager) {
        this.dataManager = dataManager;
        new Thread(() -> {
            try {
                Socket socket = new Socket(host, port);
                this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
//              set connection after connected
                dataManager.setConnectionManager(this);
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                String message = "";
                try {
                    while (!message.equals("exit")) {
                        message = dataInputStream.readUTF();
                        messageParser(message);

                    }
                } catch (IOException i) {
                    System.out.println("Server disconnected");
//                    i.printStackTrace();
                }
            } catch (IOException e) {
                System.out.println(e.toString());
//                e.printStackTrace();
            }
        }).start();
    }

    private void request(String data) {
        try {
            dataOutputStream.writeUTF(data);
        } catch (IOException e) {
//            e.printStackTrace(); ignored
        }
    }

    private void messageParser(String input) {
        String[] message = input.split("->");
        if ("getAllApps".equals(message[0])) {
            dataManager.setApps(message[1]);
        }
    }

    void getAllApps() {
        request("getAllApps");
    }

    void addApp(String input) {
        request("addApp->" + input);
    }

    void upgradeVersion(String input) {
        request("upgradeVersion->" + input);
    }

    void deactivateApp(String input) {
        request("deactivateApp->" + input);
    }

}
