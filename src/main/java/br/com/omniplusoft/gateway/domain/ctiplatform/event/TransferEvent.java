package br.com.omniplusoft.gateway.domain.ctiplatform.event;

import java.util.Map;

/**
 * Created by hermeswaldemarin on 14/12/15.
 */
public class TransferEvent extends CTIEvent {

    private String uuiToSend;
    private String destinationNumber;

    public TransferEvent(){
        super();
    }

    public TransferEvent(String uuiToSend, String destinationNumber) {
        this.uuiToSend = uuiToSend;
        this.destinationNumber = destinationNumber;
    }

    public TransferEvent(Map<String, String> arguments, String uuiToSend, String destinationNumber) {
        super(arguments);
        this.uuiToSend = uuiToSend;
        this.destinationNumber = destinationNumber;
    }

    public String getUuiToSend() {
        return uuiToSend;
    }

    public void setUuiToSend(String uuiToSend) {
        this.uuiToSend = uuiToSend;
    }

    public String getDestinationNumber() {
        return destinationNumber;
    }

    public void setDestinationNumber(String destinationNumber) {
        this.destinationNumber = destinationNumber;
    }
}
