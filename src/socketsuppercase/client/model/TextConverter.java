package socketsuppercase.client.model;

import socketsuppercase.shared.util.Subject;

public interface TextConverter extends Subject {

    void toUppercase(String text);
    void getLogs();

}


