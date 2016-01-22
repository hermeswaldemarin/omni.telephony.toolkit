package br.com.omniplusoft.gateway.domain.ctiplatform.event;

import java.util.Map;

/**
 * Created by hermeswaldemarin on 10/12/15.
 */
public class CTIEvent {
    protected Map<String, String> arguments;

    public CTIEvent() {
    }

    public CTIEvent(Map<String, String> arguments) {
        this.arguments = arguments;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, String> arguments) {
        this.arguments = arguments;
    }
}
