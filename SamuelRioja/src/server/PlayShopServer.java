package server;

import server.data.ConnectionManager;
import server.data.DataManager;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PlayShopServer {

    private DataManager dataManager = new DataManager();

    public PlayShopServer() {
        new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(5000);
                while (true) {
                    try {
                        Socket socket = server.accept();
                        new Thread(() -> {
                            try {
                                new ConnectionManager(dataManager, socket);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
