package socketsuppercase.server.networking;

import socketsuppercase.server.model.TextManager;
import socketsuppercase.shared.transferobjects.LogEntry;
import socketsuppercase.shared.transferobjects.Request;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketHandler implements Runnable {

    private Socket socket;
    private TextManager tm;

    private ObjectOutputStream outToClient;
    private ObjectInputStream inFromClient;

    public SocketHandler(Socket socket, TextManager tm) {
        this.socket = socket;
        this.tm = tm;
        tm.addListener("NewLogEntry", this::onNewLogEntry);
        try {
            outToClient = new ObjectOutputStream(socket.getOutputStream());
            inFromClient = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onNewLogEntry(PropertyChangeEvent evt) {
        try {
            outToClient.writeUnshared(new Request("NewLogEntry", evt.getNewValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while(true) {
                Request request = (Request) inFromClient.readObject();
                if("UpperCase".equals(request.getRequestType())) {
                    String result = tm.toUppercase((String) request.getArg());
                    outToClient.writeObject(new Request("UpperCase", result));
                } else if("FetchLog".equals(request.getRequestType())) {
                    System.out.println("Retrieving logs..");
                    List<LogEntry> log = tm.getLog();
                    ArrayList<LogEntry> newList = new ArrayList<>(log);
                    Request req = new Request("FetchLog", newList);
                    outToClient.writeUnshared(req);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
