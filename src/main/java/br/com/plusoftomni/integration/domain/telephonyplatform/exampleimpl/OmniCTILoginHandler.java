package br.com.plusoftomni.integration.domain.telephonyplatform.exampleimpl;

import br.com.plusoftomni.integration.domain.telephonyplatform.CTIResponse;
import br.com.plusoftomni.integration.domain.telephonyplatform.CallbackDispatcher;
import br.com.plusoftomni.integration.domain.telephonyplatform.event.LoginEvent;
import br.com.plusoftomni.integration.infrastructure.telephonyplatform.CTIEvents;
import br.com.plusoftomni.integration.infrastructure.telephonyplatform.annotation.EventHandler;
import br.com.plusoftomni.integration.infrastructure.telephonyplatform.annotation.Handle;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
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
public class OmniCTILoginHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CallbackDispatcher callbackDispatcher;

    @Handle(CTIEvents.LOGIN)
    public void execute(LoginEvent event){
        event.getUserName();

        logger.trace("LoginEvent received {}", ReflectionToStringBuilder.toString(event, ToStringStyle.MULTI_LINE_STYLE) );

        logger.info("#################testando o logger espec.....", "teste", "teste", "teste");

        callbackDispatcher.dispatch(new CTIResponse("login", 0, "Login OK", Collections.unmodifiableMap(Stream.of(
                new AbstractMap.SimpleEntry<>("arg1", "one"),
                new AbstractMap.SimpleEntry<>("arg2", "two"))
                .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())))));

        logger.trace("LOGIN Event Called");
    }
}
