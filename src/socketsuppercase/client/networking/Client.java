package socketsuppercase.client.networking;

import socketsuppercase.shared.util.Subject;

public interface Client extends Subject {
    void toUppercase(String str);
    void getLogs();

    void startClient();
}
