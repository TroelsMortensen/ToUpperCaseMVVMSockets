package javafxmvc.client.core;

import javafxmvc.client.views.log.LogModel;
import javafxmvc.client.views.uppercase.UppercaseModel;

public class ModelFactory {

    private LogModel logModel;
    private UppercaseModel uppercaseModel;

    public ModelFactory(ClientFactory cf) {
        logModel = new LogModel(cf.getClient());
        uppercaseModel = new UppercaseModel(cf.getClient());
    }

    public UppercaseModel getUppercaseModel() {
        return uppercaseModel;
    }

    public LogModel getLogModel() {
        return logModel;
    }
}
