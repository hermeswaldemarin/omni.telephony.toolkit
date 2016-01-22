package br.com.omniplusoft.gateway.infrastructure.ctiplatform;

import br.com.omniplusoft.gateway.domain.ctiplatform.event.*;

/**
 * Created by hermeswaldemarin on 14/12/15.
 */
public enum CTIEvents {

    LOGIN(LoginEvent.class),
    LOGOUT(LogoutEvent.class),
    NOTREADY(NotReadytEvent.class),
    ANSWER(AnswerEvent.class),
    MAKECALL(MakeCallEvent.class),
    DROPCALL(DropCallEvent.class),
    HOLD(HoldEvent.class),
    UNHOLD(UnHoldEvent.class),
    CONSULT(ConsultEvent.class),
    CANCELCONSULT(CancelConsultEvent.class),
    TRANSFER(TransferEvent.class),
    CONFERENCE(ConferenceEvent.class),
    READY(ReadyEvent.class),
    GENERICEVENT(GenericEvent.class);

    /*

    public void genericEvent(Map<String,String> ctiArguments) throws LoginException;;
    */

    private Class<? extends CTIEvent> ctiEventClass;

    private CTIEvents(Class<? extends CTIEvent> ctiEventClass){
        this.ctiEventClass = ctiEventClass;
    }

    public static CTIEvents getByClass(Class<? extends CTIEvent> ctiEventClass){
        for( CTIEvents event: CTIEvents.values()){
            if( event.ctiEventClass.isAssignableFrom(ctiEventClass) )
                return  event;
        }
        return null;
    }

    public Class getCtiEventClass() {
        return ctiEventClass;
    }

}
