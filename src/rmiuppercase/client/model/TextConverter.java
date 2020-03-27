package rmiuppercase.client.model;

import rmiuppercase.shared.transferobjects.LogEntry;
import rmiuppercase.shared.util.Subject;

import java.util.List;

public interface TextConverter extends Subject {

    String toUppercase(String text);
    List<LogEntry> getLogs();

}


