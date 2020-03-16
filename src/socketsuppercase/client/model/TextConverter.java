package socketsuppercase.client.model;

import socketsuppercase.shared.transferobjects.LogEntry;
import socketsuppercase.shared.util.Subject;

import java.util.List;

public interface TextConverter extends Subject {

    String toUppercase(String text);
    List<LogEntry> getLogs();

}


