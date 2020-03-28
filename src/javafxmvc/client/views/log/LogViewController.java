package javafxmvc.client.views.log;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxmvc.client.core.ModelFactory;
import javafxmvc.client.core.ViewHandler;
import javafxmvc.client.views.ViewController;
import socketsuppercase.shared.transferobjects.LogEntry;

public class LogViewController implements ViewController {

    // I can make these field public. Or make them private, and mark them with @FXML.
    // The result is the same. Private is probably preferable,
    // because if something doesn't _need_ to be public, it shouldn't be

    @FXML
    private TableView<LogEntry> tableView;
    public TableColumn<String, LogEntry> inputColumn;
    public TableColumn<String, LogEntry> outputColumn;

    private LogModel model;
    private ViewHandler vh;

    @Override
    public void init(ViewHandler vh, ModelFactory vmf) {
        this.vh = vh;
        model = vmf.getLogModel();
        model.loadLogs();
        tableView.setItems(model.getLogs());
        inputColumn.setCellValueFactory(new PropertyValueFactory<>("input"));
        outputColumn.setCellValueFactory(new PropertyValueFactory<>("output"));
    }

    public void onBackButton() {
        vh.openToUppercase();
    }
}
