package rmiuppercase.client.views;

import rmiuppercase.client.core.ViewHandler;
import rmiuppercase.client.core.ViewModelFactory;

public interface ViewController {

    void init(ViewHandler vh, ViewModelFactory vmf);

}
