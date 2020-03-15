package socketsuppercase.client.model;

import socketsuppercase.client.networking.Client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TextConverterManager implements TextConverter {

    private final Client client;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public TextConverterManager(Client client) {
        this.client = client;
        client.startClient();
        client.addListener("UpperCase", this::onUpperCaseReceived);
        client.addListener("FetchLog", this::onLogReceived);
        client.addListener("NewLogEntry", this::onNewLogEntry);
    }

    private void onNewLogEntry(PropertyChangeEvent evt) {
        support.firePropertyChange(evt);
    }

    private void onLogReceived(PropertyChangeEvent evt) {
        support.firePropertyChange(evt);
    }

    private void onUpperCaseReceived(PropertyChangeEvent evt) {
        support.firePropertyChange(evt);
    }

    @Override
    public void toUppercase(String text) {
        client.toUppercase(text);
    }

    @Override
    public void getLogs() {
        client.getLogs();
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


