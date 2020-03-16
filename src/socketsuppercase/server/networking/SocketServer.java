package socketsuppercase.server.networking;

import socketsuppercase.server.model.TextManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private TextManager textManager;

    public SocketServer(TextManager textManager) {
        this.textManager = textManager;
    }

    public void startServer() {
        try {
            ServerSocket welcomeSocket = new ServerSocket(2910);

            while(true) {
                Socket socket = welcomeSocket.accept();
                new Thread(new SocketHandler(socket, textManager)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
