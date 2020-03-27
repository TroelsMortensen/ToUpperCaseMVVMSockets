package rmiuppercase.client.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rmiuppercase.client.views.ViewController;

import java.io.IOException;

public class ViewHandler {

    private Scene uppercaseScene;
    private Stage stage;
    private ViewModelFactory vmf;
    private Scene logScene;

    public ViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
    }

    public void start() {
        stage = new Stage();
        openToUppercase();
    }

    public void openToUppercase() {
        if (uppercaseScene == null) {
            try {
                Parent root = loadFXML("../views/uppercase/UppercaseView.fxml");

                stage.setTitle("Upper case");
                uppercaseScene = new Scene(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(uppercaseScene);
        stage.show();
    }

    public void openLog() {
        // no reusing a logScene, because I want the log to reload the latest every time.
        if (logScene == null) {
            try {
                Parent root = loadFXML("../views/log/Log.fxml");

                logScene = new Scene(root);
                stage.setTitle("Log");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(logScene);
        stage.show();
    }

    private Parent loadFXML(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        ViewController ctrl = loader.getController();
        ctrl.init(this, vmf);
        return root;
    }
}
