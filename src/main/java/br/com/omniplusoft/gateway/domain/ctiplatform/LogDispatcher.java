package br.com.omniplusoft.gateway.domain.ctiplatform;

import ch.qos.logback.classic.spi.LoggingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hermeswaldemarin on 11/12/15.
 */
@Service
public class LogDispatcher {

    @Autowired
    private MessageSendingOperations<String> messagingTemplate;

    public void dispatch(LoggingEvent loggingEvent) {
        this.messagingTemplate.convertAndSend("/messages/cti/log", toOmniMap(loggingEvent));
    }

    protected Map<String,String> toOmniMap(LoggingEvent event) {

        final HashMap<String, String> doc = new HashMap();

        doc.put("level", event.getLevel().levelStr);

        doc.put("message", event.getFormattedMessage());

        doc.put("logger", event.getLoggerName());

        return doc;

    }

}
