package socketsuppercase.server.networking;

import socketsuppercase.server.model.TextManager;
import socketsuppercase.shared.transferobjects.LogEntry;
import socketsuppercase.shared.transferobjects.Request;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class SocketHandler implements Runnable {

    private Socket socket;
    private TextManager textManager;

    private ObjectOutputStream outToClient;
    private ObjectInputStream inFromClient;

    public SocketHandler(Socket socket, TextManager textManager) {
        this.socket = socket;
        this.textManager = textManager;

        try {
            outToClient = new ObjectOutputStream(socket.getOutputStream());
            inFromClient = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Request request = (Request) inFromClient.readObject();
            if("Listener".equals(request.getType())) {
                textManager.addListener("NewLogEntry", this::onNewLogEntry);
            } else if("Uppercase".equals(request.getType())) {
                String result = textManager.toUppercase((String) request.getArg());
                outToClient.writeObject(new Request("Uppercase", result));
            } else if("FetchLog".equals(request.getType())) {
                List<LogEntry> log = textManager.getLog();
                outToClient.writeObject(new Request("FetchLog", log));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void onNewLogEntry(PropertyChangeEvent evt) {
        try {
            outToClient.writeObject(new Request(evt.getPropertyName(), evt.getNewValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
