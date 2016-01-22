package br.com.omniplusoft.gateway.domain.ctiplatform.event;

/**
 * Created by hermeswaldemarin on 14/12/15.
 */
public class ConferenceEvent extends CTIEvent {

    private String callNumber;

    public ConferenceEvent() {
        super();
    }

    public ConferenceEvent(String callNumber){
        super();
        this.callNumber = callNumber;
    }

    public ConferenceEvent(String callNumber, java.util.Map<String, String> arguments){
        super(arguments);
        this.callNumber = callNumber;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

}
