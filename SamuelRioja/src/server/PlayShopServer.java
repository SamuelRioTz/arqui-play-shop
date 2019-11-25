package server;

import server.data.ConnectionManager;
import server.data.Database;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PlayShopServer {

    private Database database = new Database();

    public PlayShopServer() {
        new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(5000);
                while (true) {
                    try {
                        Socket socket = server.accept();
                        new Thread(() -> {
                            try {
                                new ConnectionManager(database, socket);
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
