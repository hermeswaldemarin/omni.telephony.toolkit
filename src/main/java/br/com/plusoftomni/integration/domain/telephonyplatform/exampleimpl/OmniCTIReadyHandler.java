package br.com.plusoftomni.integration.domain.telephonyplatform.exampleimpl;

import br.com.plusoftomni.integration.domain.telephonyplatform.event.ReadyEvent;
import br.com.plusoftomni.integration.domain.telephonyplatform.CTIResponse;
import br.com.plusoftomni.integration.domain.telephonyplatform.CallbackDispatcher;
import br.com.plusoftomni.integration.infrastructure.telephonyplatform.CTIEvents;
import br.com.plusoftomni.integration.infrastructure.telephonyplatform.annotation.EventHandler;
import br.com.plusoftomni.integration.infrastructure.telephonyplatform.annotation.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hermeswaldemarin on 14/12/15.
 */
@EventHandler
@Profile("example")
public class OmniCTIReadyHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CallbackDispatcher callbackDispatcher;

    @Handle(CTIEvents.READY)
    public void execute(ReadyEvent event){
        event.getArguments();

        OmniCTIExampleListener.runRinging = true;

        callbackDispatcher.dispatch(new CTIResponse("ready", 0, "Now you are available.", Collections.unmodifiableMap(Stream.of(
                new AbstractMap.SimpleEntry<>("arg1", "one"),
                new AbstractMap.SimpleEntry<>("arg2", "two"))
                .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue()))))
        );

        logger.trace("BECOMEAVAILABLE Event Called");
    }
}
