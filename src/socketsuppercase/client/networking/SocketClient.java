package socketsuppercase.client.networking;

import socketsuppercase.shared.transferobjects.Request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient implements Client{

    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;

    private PropertyChangeSupport support;

    public SocketClient() {
        support = new PropertyChangeSupport(this);
    }

    public void startClient() {
        try {
            Socket socket = new Socket("localhost", 2910);
            outToServer = new ObjectOutputStream(socket.getOutputStream());
            inFromServer = new ObjectInputStream(socket.getInputStream());

            Thread t = new Thread(this::listenToServer);
            t.setDaemon(true);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenToServer() {
        try {
            while(true) {
                Request request = (Request) inFromServer.readUnshared();

                support.firePropertyChange(request.getRequestType(), null, request.getArg());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toUppercase(String str) {
        try {
            Request req = new Request("UpperCase", str);
            outToServer.writeObject(req);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getLogs() {
        try {
            Request req = new Request("FetchLog", null);
            outToServer.writeObject(req);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
