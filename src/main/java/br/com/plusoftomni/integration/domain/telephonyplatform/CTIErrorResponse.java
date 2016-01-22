package br.com.plusoftomni.integration.domain.telephonyplatform;

/**
 * Created by hermeswaldemarin on 10/12/15.
 */
public class CTIErrorResponse extends CTIResponse{

    public CTIErrorResponse() {
    }

    public CTIErrorResponse(String returnMessage, Throwable e) {
        this.returnMessage = returnMessage;
        this.returnMessage += "\n";
        this.returnMessage += e.getMessage();
        this.returnCode = 1;
        this.eventName = "error";
    }

}
