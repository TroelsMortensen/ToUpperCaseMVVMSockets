package socketsuppercase.shared.transferobjects;

import java.io.Serializable;

public class Request implements Serializable {
    private String requestType;
    private Object arg;

    public Request(String requestType, Object arg) {
        this.requestType = requestType;
        this.arg = arg;
    }

    public String getRequestType() {
        return requestType;
    }

    public Object getArg() {
        return arg;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestType='" + requestType + '\'' +
                ", arg=" + arg +
                '}';
    }
}
