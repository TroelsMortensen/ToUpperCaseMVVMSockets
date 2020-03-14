package localuppercase.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class TextConverterManager implements TextConverter {

    private List<InputOutput> logs = new ArrayList<>();
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public String toUppercase(String text) {
        String result = text.toUpperCase();
        InputOutput log = new InputOutput(text, result);
        logs.add(log);
        support.firePropertyChange("LogAdded", null, log);
        return result;
    }

    @Override
    public List<InputOutput> getLogs() {
        return logs;
    }


    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}


