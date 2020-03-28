package javafxmvc.client.networking;

import socketsuppercase.shared.transferobjects.LogEntry;
import socketsuppercase.shared.util.Subject;

import java.util.List;
import java.util.function.Consumer;

public interface Client extends Subject {
    void toUppercase(String str, Consumer<String> callback);
    void getLog(Consumer<List<LogEntry>> callback);
}
