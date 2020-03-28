package javafxmvc.client.views.uppercase;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafxmvc.client.core.ModelFactory;
import javafxmvc.client.core.ViewHandler;
import javafxmvc.client.views.ViewController;

public class UppercaseViewController implements ViewController {
    @FXML
    private Label errorLabel;
    @FXML
    private TextField requestField;
    @FXML
    private TextField replyField;

    private UppercaseModel model;
    private ViewHandler vh;

    public void init(ViewHandler vh, ModelFactory mf) {
        this.vh = vh;
        this.model = mf.getUppercaseModel();
        replyField.setDisable(true);
        errorLabel.textProperty().bind(model.errorProperty());
        requestField.textProperty().bindBidirectional(model.requestProperty());
        replyField.textProperty().bind(model.replyProperty());
    }

    @FXML
    private void onSubmitButton() {
        model.convert();
    }

    // I can make this method public, or do like above, make it private and mark it @FXML.
    // The result is the same
    public void onLogButton() {
        vh.openLog();
    }
}

