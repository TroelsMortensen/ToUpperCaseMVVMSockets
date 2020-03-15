package socketsuppercase.client.core;

import socketsuppercase.client.networking.Client;
import socketsuppercase.client.networking.SocketClient;

public class ClientFactory {

    private Client client;

    public Client getClient() {
        if(client == null) client = new SocketClient();
        return client;
    }
}
