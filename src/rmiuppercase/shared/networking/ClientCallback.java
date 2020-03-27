package rmiuppercase.shared.networking;


import rmiuppercase.shared.transferobjects.LogEntry;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote {
    void update(LogEntry log) throws RemoteException;
}
