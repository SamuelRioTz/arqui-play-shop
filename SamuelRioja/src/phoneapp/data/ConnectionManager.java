package phoneapp.data;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionManager {
    private DataOutputStream dataOutputStream;

    private DataManager dataManager;


    public ConnectionManager(DataManager dataManager) {
        this.dataManager = dataManager;
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 5000);
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
        if ("getAllActiveApps".equals(message[0])) {
            dataManager.setApps(message[1]);
        }
    }

    void getAllActiveApps() {
        request("getAllActiveApps");
    }
}
