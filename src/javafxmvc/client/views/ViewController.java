package javafxmvc.client.views;


import javafxmvc.client.core.ModelFactory;
import javafxmvc.client.core.ViewHandler;

public interface ViewController {

    void init(ViewHandler vh, ModelFactory vmf);

}
