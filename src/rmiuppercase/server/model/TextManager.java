package rmiuppercase.server.model;

import rmiuppercase.shared.transferobjects.LogEntry;
import rmiuppercase.shared.util.Subject;

import java.util.List;

public interface TextManager extends Subject {

    String toUppercase(String str);
    List<LogEntry> getLog();
}
