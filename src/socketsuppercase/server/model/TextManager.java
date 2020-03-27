package socketsuppercase.server.model;


import socketsuppercase.shared.transferobjects.LogEntry;
import socketsuppercase.shared.util.Subject;

import java.util.List;

public interface TextManager extends Subject {

    String toUppercase(String str);
    List<LogEntry> getLog();
}
