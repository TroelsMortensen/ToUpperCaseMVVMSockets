package javafxmvc.client.core;

import javafxmvc.client.networking.Client;
import javafxmvc.client.networking.SocketClient;

public class ClientFactory {
    private Client client = new SocketClient();

    public Client getClient() {
        return client;
    }
}
