package localuppercase;

import javafx.application.Application;
import javafx.stage.Stage;
import localuppercase.core.ModelFactory;
import localuppercase.core.ViewHandler;
import localuppercase.core.ViewModelFactory;

public class UppercaseApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ModelFactory mf = new ModelFactory();
        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler vh = new ViewHandler(vmf);
        vh.start();
    }
}
