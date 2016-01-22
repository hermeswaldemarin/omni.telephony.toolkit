package br.com.omniplusoft.gateway.domain.ctiplatform.event;

import org.hibernate.mapping.Map;

/**
 * Created by hermeswaldemarin on 14/12/15.
 */
public class MakeCallEvent extends CTIEvent {

    private String callNumber;

    public MakeCallEvent() {
        super();
    }

    public MakeCallEvent(String callNumber){
        super();
        this.callNumber = callNumber;
    }

    public MakeCallEvent(String callNumber, java.util.Map<String, String> arguments){
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
