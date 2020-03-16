package socketsuppercase.client.core;

import socketsuppercase.client.network.Client;
import socketsuppercase.client.network.SocketClient;

public class ClientFactory {

    private Client client;

    public Client getClient() {
        if(client == null) {
            client = new SocketClient();
        }
        return client;
    }
}
