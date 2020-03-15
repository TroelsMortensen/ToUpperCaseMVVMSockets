package socketsuppercase.client.views.uppercase;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import socketsuppercase.client.model.TextConverter;

import java.beans.PropertyChangeEvent;

public class UppercaseViewModel {
    private StringProperty request, reply, error;
    private TextConverter textConverter;

    public UppercaseViewModel(TextConverter textConverter) {
        this.textConverter = textConverter;
        request = new SimpleStringProperty();
        reply = new SimpleStringProperty();
        error = new SimpleStringProperty();
        textConverter.addListener("UpperCase", this::onUppercase);
    }

    private void onUppercase(PropertyChangeEvent evt) {
        reply.set((String)evt.getNewValue());
    }

    void convert() {
        String input = request.get();
        if(input != null && !"".equals(input)) {
            textConverter.toUppercase(input);
        } else {
            error.set("Input cannot be empty");
        }
    }

    StringProperty requestProperty() {
        return request;
    }

    StringProperty replyProperty() {
        return reply;
    }

    StringProperty errorProperty() {
        return error;
    }
}
