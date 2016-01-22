package br.com.omniplusoft.gateway.domain.ctiplatform;

import java.util.Map;

/**
 * Created by hermeswaldemarin on 10/12/15.
 */
public class CTIStatusResponse extends CTIResponse{

    public CTIStatusResponse() {
    }

    public CTIStatusResponse(String message) {
        this.returnMessage = message;
        this.returnCode = 0;
        this.eventName = "status";
    }

    public CTIStatusResponse(String message, Map<String, String> arguments) {
        this(message);
        this.arguments = arguments;
    }

}
