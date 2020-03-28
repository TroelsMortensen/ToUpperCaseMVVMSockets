package javafxmvc.client.views.uppercase;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafxmvc.client.networking.Client;

import java.util.function.Consumer;

public class UppercaseModel {

    private Client client;

    private StringProperty error;
    private StringProperty request;
    private StringProperty reply;

    public UppercaseModel(Client client) {
        this.client = client;
        error=new SimpleStringProperty();
        request=new SimpleStringProperty();
        reply=new SimpleStringProperty();
    }

    void convert() {
        client.toUppercase(request.get(), s ->{
            Platform.runLater(() -> reply.set(s));
        });
    }

    StringProperty errorProperty() {
        return error;
    }

    StringProperty requestProperty() {
        return request;
    }

    StringProperty replyProperty() {
        return reply;
    }
}
