package br.com.omniplusoft.gateway.application.ctiplatform;

import br.com.omniplusoft.gateway.domain.ctiplatform.CTIEventHandler;
import br.com.omniplusoft.gateway.domain.ctiplatform.event.CTIEvent;
import br.com.omniplusoft.gateway.domain.ctiplatform.exceptions.DispatchExecutionException;
import br.com.omniplusoft.gateway.infrastructure.ctiplatform.CTIEvents;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by hermeswaldemarin on 10/12/15.
 */
@RestController
public class CTIController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageSendingOperations<String> messagingTemplate;

    @Autowired
    private CTIEventHandler handler;

    @MessageMapping("/cti/command/{eventName}")
    public void executeCommand(@DestinationVariable String eventName, Message eventContent) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            CTIEvent ctiEvent = (CTIEvent) mapper.readValue(new String((byte[])eventContent.getPayload()), CTIEvents.valueOf(eventName.toUpperCase()).getCtiEventClass());
            handler.dispatch(ctiEvent);
        } catch (IOException e) {
            throw new DispatchExecutionException(e);
        }finally {
            mapper = null;
        }
    }

}
