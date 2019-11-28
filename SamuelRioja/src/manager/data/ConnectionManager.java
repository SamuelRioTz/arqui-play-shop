package manager.data;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionManager {
    private DataManager dataManager;
    private DataOutputStream dataOutputStream;


    public ConnectionManager(DataManager dataManager) {
        this.dataManager = dataManager;
        new Thread(() -> {
            try {
                Socket socket = new Socket("localhost", 5000);
                this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
//              set connection after connected
                dataManager.setConnectionManager(this);
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

    private void request(String data) {
        try {
            dataOutputStream.writeUTF(data);
        } catch (IOException e) {
            e.printStackTrace();
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

     void addApp(String input) {request("addApp->" + input);
    }
    void upgradeVersion(String input) {
        request("upgradeVersion->" + input);
    }

    void deactivateApp(String input) {
        request("deactivateApp->" + input);
    }

}
