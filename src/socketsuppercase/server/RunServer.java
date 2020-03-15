package socketsuppercase.server;

import socketsuppercase.server.model.TextManager;
import socketsuppercase.server.model.TextManagerImpl;
import socketsuppercase.server.networking.SocketServer;

public class RunServer {
    public static void main(String[] args) {
        TextManager tm = new TextManagerImpl();
        SocketServer ss =new SocketServer(tm);
        ss.startServer();
    }
}
