package br.com.omniplusoft.gateway.domain.ctiplatform.event;

/**
 * Created by hermeswaldemarin on 14/12/15.
 */
public class ReadyEvent extends CTIEvent {

    private String reasonCode;

    public ReadyEvent() {
        super();
    }

    public ReadyEvent(String reasonCode) {

        this.reasonCode = reasonCode;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

}
