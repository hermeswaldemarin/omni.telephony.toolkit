package br.com.omniplusoft.gateway.infrastructure.config;

import br.com.omniplusoft.gateway.domain.ctiplatform.LogDispatcher;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by hermeswaldemarin on 15/12/15.
 */
@Component
public class OmniGatewayCTIAppender<E> extends ConsoleAppender<E> {

    ApplicationContext context;

    LogDispatcher logDispatcher;

    public synchronized void doAppend(E eventObject) {
        context = MyClassWithEventListeners.applicationContext;

        if( ((LoggingEvent)eventObject).getLoggerName().contains("br.com.omniplusoft") ) {
            if(context != null){
                //context.getBeanDefinitionNames()
                logDispatcher = context.getBean(LogDispatcher.class);
            }

            if(logDispatcher != null){
                logDispatcher.dispatch((LoggingEvent) eventObject);
                //System.out.println("teste: " + eventObject.toString());
            }
        }

    }

}
