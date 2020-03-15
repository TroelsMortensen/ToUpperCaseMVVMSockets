package socketsuppercase.client.views.log;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import socketsuppercase.shared.transferobjects.LogEntry;
import socketsuppercase.client.model.TextConverter;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class LogViewModel {

    private ObservableList<LogEntry> logs;

    private TextConverter textConverter;

    public LogViewModel(TextConverter textConverter) {
        this.textConverter = textConverter;
        textConverter.addListener("FetchLog", this::onLogReceived);
        textConverter.addListener("NewLogEntry", this::onNewEntry);
    }

    private void onNewEntry(PropertyChangeEvent evt) {
        logs.add((LogEntry) evt.getNewValue());
    }

    private void onLogReceived(PropertyChangeEvent evt) {
        System.out.println("\nLogViewModel: " + evt.getNewValue());
        logs.addAll((List<LogEntry>)evt.getNewValue());
    }

    void loadLogs() {
        logs = FXCollections.observableArrayList();
        textConverter.getLogs();
    }

    ObservableList<LogEntry> getLogs() {
        return logs;
    }
}
