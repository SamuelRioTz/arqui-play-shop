package server;

import server.data.ConnectionManager;
import server.data.DataManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class PlayShopServer {

    private DataManager dataManager = new DataManager();
    private int port = 50000;

    PlayShopServer() {
        new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(port);
                System.out.println("Server running on: " + port);
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
