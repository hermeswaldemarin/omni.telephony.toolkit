package br.com.plusoftomni.integration.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SpringEventListeners {

    @Autowired
    ApplicationContext context;

    public static ApplicationContext applicationContext;

    @EventListener({ContextRefreshedEvent.class})
    void contextRefreshedEvent() {
        applicationContext = context;
        System.out.println("a context refreshed event happened " + context);
    }
}