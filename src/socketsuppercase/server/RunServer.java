package socketsuppercase.server;

import socketsuppercase.server.model.TextManagerImpl;
import socketsuppercase.server.networking.SocketServer;

public class RunServer {
    public static void main(String[] args) {

        SocketServer ss = new SocketServer(new TextManagerImpl());
        ss.startServer();
    }
}
