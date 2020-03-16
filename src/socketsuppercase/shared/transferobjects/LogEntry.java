package socketsuppercase.shared.transferobjects;

import java.io.Serializable;

public class LogEntry implements Serializable {

    private String input, output;

    public LogEntry(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }
}
