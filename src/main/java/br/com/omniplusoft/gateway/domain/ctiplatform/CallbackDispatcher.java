package br.com.omniplusoft.gateway.domain.ctiplatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Service;

/**
 * Created by hermeswaldemarin on 11/12/15.
 */
@Service
public class CallbackDispatcher {

    @Autowired
    private MessageSendingOperations<String> messagingTemplate;

    public void dispatch(CTIResponse response) {
        if(this.messagingTemplate != null)
            this.messagingTemplate.convertAndSend("/messages/cti/eventcallback", response);
    }
}
