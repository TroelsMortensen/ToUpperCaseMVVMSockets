package rmiuppercase.server.networking;

import rmiuppercase.server.model.TextManager;
import rmiuppercase.shared.networking.ClientCallback;
import rmiuppercase.shared.networking.RMIServer;
import rmiuppercase.shared.transferobjects.LogEntry;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RMIServerImpl implements RMIServer {

    private final TextManager textManager;
    private Map<ClientCallback, PropertyChangeListener> listeners = new HashMap<>();

    public RMIServerImpl(TextManager textManager) throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.textManager = textManager;
    }

    public void startServer() throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("UppercaseServer", this);
    }

    @Override
    public String toUppercase(String str) {
        return textManager.toUppercase(str);
    }

    @Override
    public List<LogEntry> getLogs() {
        return textManager.getLog();
    }

    @Override
    public void registerClient(ClientCallback client)  {
        PropertyChangeListener listener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try {
                    client.update((LogEntry) evt.getNewValue());
                } catch (RemoteException e) {
                    e.printStackTrace();
                    textManager.removeListener("NewLogEntry", this);
                }
            }
        };
        listeners.put(client, listener);
        textManager.addListener("NewLogEntry", listener);
    }

    @Override
    public void unRegisterClient(ClientCallback client) {
        PropertyChangeListener listener = listeners.get(client);
        if(listener != null) {
            textManager.removeListener("NewLogEntry", listener);
        }
        int stopher=0;
    }
}
