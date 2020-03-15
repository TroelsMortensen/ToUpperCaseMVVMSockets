package localuppercase.views.log;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import localuppercase.model.LogEntry;
import localuppercase.model.TextConverter;

import java.util.List;

public class LogViewModel {

    private ObservableList<LogEntry> logs;

    private TextConverter textConverter;

    public LogViewModel(TextConverter textConverter) {
        this.textConverter = textConverter;
    }

    void loadLogs() {
        List<LogEntry> logList = textConverter.getLogs();
        logs = FXCollections.observableArrayList(logList);
    }

    ObservableList<LogEntry> getLogs() {
        return logs;
    }
}
