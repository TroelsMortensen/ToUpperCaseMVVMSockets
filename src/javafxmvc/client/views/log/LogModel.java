package javafxmvc.client.views.log;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafxmvc.client.networking.Client;
import socketsuppercase.shared.transferobjects.LogEntry;

import java.beans.PropertyChangeEvent;

public class LogModel {

    private ObservableList<LogEntry> logs;
    private Client client;

    public LogModel(Client client) {
        this.client = client;
        logs = FXCollections.observableArrayList();
        client.addListener("NewLogEntry", evt -> logs.add((LogEntry)evt.getNewValue()));
    }

    void loadLogs() {
        client.getLog((logEntries) -> {
            logs.clear();
            logs.addAll(logEntries);
        });
    }

    ObservableList<LogEntry> getLogs() {
        return logs;
    }
}
