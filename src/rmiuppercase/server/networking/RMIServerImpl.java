package rmiuppercase.server.networking;

import rmiuppercase.server.model.TextManager;
import rmiuppercase.shared.networking.ClientCallback;
import rmiuppercase.shared.networking.RMIServer;
import rmiuppercase.shared.transferobjects.LogEntry;

import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIServerImpl implements RMIServer {

    private final TextManager textManager;

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
        PropertyChangeListener listener = null;
        PropertyChangeListener finalListener = listener;

        listener = evt -> {
            try {
                client.update((LogEntry) evt.getNewValue());
            } catch (RemoteException e) {
                e.printStackTrace();
                textManager.removeListener("NewLogEntry", finalListener);
            }
        };
        textManager.addListener("NewLogEntry", listener);
    }
}