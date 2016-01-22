package br.com.omniplusoft.gateway.application.ctiplatform.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurationSupport;
import org.springframework.web.socket.server.RequestUpgradeStrategy;
import org.springframework.web.socket.server.standard.TomcatRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@ComponentScan("br.com.omniplusoft.gateway.application.ctiplatform, br.com.omniplusoft.gateway.domain")
public class TestWebSocketConfig extends WebSocketMessageBrokerConfigurationSupport {

    @Override
    protected void registerStompEndpoints(StompEndpointRegistry registry) {
        // Can't rely on classpath detection
        RequestUpgradeStrategy upgradeStrategy = new TomcatRequestUpgradeStrategy();
        registry.addEndpoint("/sockjs")
                .setHandshakeHandler(new DefaultHandshakeHandler(upgradeStrategy))
                .setAllowedOrigins("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry configurer) {
        configurer.setApplicationDestinationPrefixes("/gateway");
        configurer.enableSimpleBroker("/messages");
    }
}