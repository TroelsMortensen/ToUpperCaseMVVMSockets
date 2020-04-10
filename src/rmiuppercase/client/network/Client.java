package rmiuppercase.client.network;

import rmiuppercase.shared.transferobjects.LogEntry;
import rmiuppercase.shared.util.Subject;

import java.util.List;

public interface Client extends Subject {

    String toUppercase(String str);
    List<LogEntry> getLog();

    void startClient();

    void unRegisterClient();
}
