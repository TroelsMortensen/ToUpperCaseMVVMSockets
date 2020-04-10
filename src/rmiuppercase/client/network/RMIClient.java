package rmiuppercase.client.network;

import rmiuppercase.shared.networking.ClientCallback;
import rmiuppercase.shared.networking.RMIServer;
import rmiuppercase.shared.transferobjects.LogEntry;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIClient implements Client, ClientCallback {

    private RMIServer server;
    private PropertyChangeSupport support;

    public RMIClient() {
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void startClient() {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            server = (RMIServer) registry.lookup("UppercaseServer");
            server.registerClient(this);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unRegisterClient() {
        try {
            server.unRegisterClient(this);
        } catch (RemoteException e) {
        }
    }

    @Override
    public String toUppercase(String str) {
        try {
            return server.toUppercase(str);
        } catch (RemoteException e) {
            throw new RuntimeException("Could not contact server");
        }
    }

    @Override
    public List<LogEntry> getLog() {
        try {
            return server.getLogs();
        } catch (RemoteException e) {
            throw new RuntimeException("Could not contact server");
        }
    }

    @Override
    public void update(LogEntry log) {
        support.firePropertyChange("NewLogEntry", null, log);
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
