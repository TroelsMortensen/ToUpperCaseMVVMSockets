package javafxmvc.client.networking;

import socketsuppercase.shared.transferobjects.LogEntry;
import socketsuppercase.shared.transferobjects.Request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.function.Consumer;

public class SocketClient implements Client {

    private PropertyChangeSupport support;

    public SocketClient() {
        support = new PropertyChangeSupport(this);
        try {
            Socket socket = new Socket("localhost", 2910);
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

            new Thread(() -> listenToServer(outToServer, inFromServer)).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenToServer(ObjectOutputStream outToServer, ObjectInputStream inFromServer) {
        try {
            outToServer.writeObject(new Request("Listener", null));
            while (true) {
                Request request = (Request) inFromServer.readObject();
                support.firePropertyChange(request.getType(), null, request.getArg());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toUppercase(String str, Consumer<String> callback) {
        new Thread(() -> {
            try {
                Request response = request(str, "Uppercase");
                callback.accept((String) response.getArg());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void getLog(Consumer<List<LogEntry>> callback) {
        new Thread(() -> {
            try {
                Request response = request(null, "FetchLog");
                callback.accept((List<LogEntry>) response.getArg());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }

    private Request request(String arg, String type) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 2910);
        ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
        outToServer.writeObject(new Request(type, arg));
        return (Request) inFromServer.readObject();
    }
}
