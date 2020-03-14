package localuppercase.views.log;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import localuppercase.model.InputOutput;
import localuppercase.model.TextConverter;

import java.util.List;

public class LogViewModel {

    private ObservableList<InputOutput> logs;

    private TextConverter textConverter;

    public LogViewModel(TextConverter textConverter) {
        this.textConverter = textConverter;
    }

    void loadLogs() {
        List<InputOutput> logList = textConverter.getLogs();
        logs = FXCollections.observableArrayList(logList);
    }

    ObservableList<InputOutput> getLogs() {
        return logs;
    }
}
