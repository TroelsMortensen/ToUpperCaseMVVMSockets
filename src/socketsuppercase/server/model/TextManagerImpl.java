package socketsuppercase.server.model;

import socketsuppercase.shared.transferobjects.LogEntry;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class TextManagerImpl implements TextManager {

    private List<LogEntry> log = new ArrayList<>();
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public String toUppercase(String str) {
        String result = str.toUpperCase();
        LogEntry entry = new LogEntry(str, result);
        log.add(entry);
        support.firePropertyChange("NewLogEntry", null, entry);
        return result;
    }

    @Override
    public List<LogEntry> getLog() {
        return log;
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
