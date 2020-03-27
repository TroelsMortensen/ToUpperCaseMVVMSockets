package rmiuppercase.client;

import javafx.application.Application;
import javafx.stage.Stage;
import rmiuppercase.client.core.ClientFactory;
import rmiuppercase.client.core.ModelFactory;
import rmiuppercase.client.core.ViewHandler;
import rmiuppercase.client.core.ViewModelFactory;

public class UppercaseApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ClientFactory cf = new ClientFactory();
        ModelFactory mf = new ModelFactory(cf);
        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler vh = new ViewHandler(vmf);
        vh.start();
    }
}
