package br.com.omniplusoft.gateway.domain.ctiplatform.exampleimpl;

import br.com.omniplusoft.gateway.domain.ctiplatform.CTIResponse;
import br.com.omniplusoft.gateway.domain.ctiplatform.CallbackDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hermeswaldemarin on 11/12/15.
 */
@Service
@Profile("example")
public class OmniCTIExampleListener {

    public static boolean runRinging = false;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CallbackDispatcher eventDispatcher;


    @Scheduled(fixedDelay=10000)
    public void sendEvent() {

        if(runRinging){
            OmniCTIExampleListener.runRinging = false;

            eventDispatcher.dispatch(new CTIResponse("ring", 0, "ringing...", Collections.unmodifiableMap(Stream.of(
                    new AbstractMap.SimpleEntry<>("uui", "uui teste"),
                    new AbstractMap.SimpleEntry<>("origin", "11994017312"),
                    new AbstractMap.SimpleEntry<>("calledNumber", "50912777"))
                    .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())))));
        }

    }

}


