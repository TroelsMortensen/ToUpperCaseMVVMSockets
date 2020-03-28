package javafxmvc.client;

import javafx.application.Application;
import javafx.stage.Stage;
import javafxmvc.client.core.ClientFactory;
import javafxmvc.client.core.ModelFactory;
import javafxmvc.client.core.ViewHandler;

public class JavaFXMVCApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new ViewHandler(new ModelFactory(new ClientFactory())).start();
    }
}
