package br.com.plusoftomni.integration.infrastructure.config;

import br.com.plusoftomni.integration.domain.telephonyplatform.LogDispatcher;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by hermeswaldemarin on 15/12/15.
 */
@Component
public class OmniTelephonyToolkitAppender<E> extends ConsoleAppender<E> {

    ApplicationContext context;

    LogDispatcher logDispatcher;

    public synchronized void doAppend(E eventObject) {
        context = SpringEventListeners.applicationContext;

        if( ((LoggingEvent)eventObject).getLoggerName().contains("br.com.plusoftomni") ) {
            if(context != null){
                //context.getBeanDefinitionNames()
                logDispatcher = context.getBean(LogDispatcher.class);
            }

            if(logDispatcher != null){
                logDispatcher.dispatch((LoggingEvent) eventObject);
            }
        }

    }

}
