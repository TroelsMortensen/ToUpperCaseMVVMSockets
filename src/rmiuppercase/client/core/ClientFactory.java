package rmiuppercase.client.core;

import rmiuppercase.client.network.Client;
import rmiuppercase.client.network.RMIClient;

public class ClientFactory {

    private Client client;

    public Client getClient() {
        if(client == null) {
            client = new RMIClient();
        }
        return client;
    }
}
