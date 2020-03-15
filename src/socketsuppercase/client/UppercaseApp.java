package socketsuppercase.client;

import javafx.application.Application;
import javafx.stage.Stage;
import socketsuppercase.client.core.ClientFactory;
import socketsuppercase.client.core.ModelFactory;
import socketsuppercase.client.core.ViewHandler;
import socketsuppercase.client.core.ViewModelFactory;

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
