package playshoplib;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class ConnectionClientManager {
    private DataOutputStream dataOutputStream;
    private String host;
    private int port;

    private ResponseListener responseListener;

    public ConnectionClientManager(String host, int port) {
        this.host = host;
        this.port = port;
        startConnection();
    }

    private void startConnection() {
        new Thread(() -> {
            try {
                Socket socket = new Socket(host, port);
                this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
//              set connection after connected
                onConnection();
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                String message = "";
                try {
                    while (!message.equals("exit")) {
                        message = dataInputStream.readUTF();
                        onReceiveResponse(message);
                    }
                } catch (IOException e) {
                    System.out.println("Server disconnected");
//                    e.printStackTrace();
                }
            } catch (IOException e) {
                System.out.println(e.toString());
//                e.printStackTrace();
            }
        }).start();
    }

    private void onReceiveResponse(String input) {
        if (responseListener != null) {
            responseListener.onResponse(input);
            responseListener = null;
        }
    }

    public void request(String data, ResponseListener responseListener) {
        try {
            this.responseListener = responseListener;
            dataOutputStream.writeUTF(data);
        } catch (IOException e) {
//            e.printStackTrace(); ignored
        }
    }

    abstract public void onConnection();

}
