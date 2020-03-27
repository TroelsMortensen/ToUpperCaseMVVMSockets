package rmiuppercase.shared.networking;

import rmiuppercase.shared.transferobjects.LogEntry;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIServer extends Remote {
    String toUppercase(String str) throws RemoteException;
    List<LogEntry> getLogs() throws RemoteException;
    void registerClient(ClientCallback client) throws RemoteException;
}
