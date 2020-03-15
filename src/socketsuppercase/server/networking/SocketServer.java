package socketsuppercase.server.networking;

import socketsuppercase.server.model.TextManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private final TextManager tm;

    public SocketServer(TextManager tm) {
        this.tm = tm;
    }

    public void startServer() {
        try {
            ServerSocket welcomeSocket = new ServerSocket(2910);
            System.out.println("Server started");
            while(true){
                System.out.println("Waiting for client");
                Socket socket = welcomeSocket.accept();
                new Thread(new SocketHandler(socket, tm)).start();
                System.out.println("Client connected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
