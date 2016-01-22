package br.com.omniplusoft.gateway.domain.ctiplatform;

import java.util.Map;

/**
 * Created by hermeswaldemarin on 10/12/15.
 */
public class CTIResponse {
    protected String eventName;
    protected Integer returnCode;
    protected String returnMessage;
    protected Map<String, String> arguments;

    public CTIResponse() {
    }

    public CTIResponse(String eventName, Integer returnCode, String returnMessage) {
        this.eventName = eventName;
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }

    public CTIResponse(String eventName, Integer returnCode, String returnMessage, Map<String, String> arguments) {
        this.eventName = eventName;
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
        this.arguments = arguments;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, String> arguments) {
        this.arguments = arguments;
    }
}
