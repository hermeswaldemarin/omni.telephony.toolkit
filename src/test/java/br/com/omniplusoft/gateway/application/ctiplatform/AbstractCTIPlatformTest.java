package br.com.omniplusoft.gateway.application.ctiplatform;

import br.com.omniplusoft.gateway.application.ctiplatform.config.TestWebSocketConfig;
import br.com.omniplusoft.gateway.application.ctiplatform.exampleimpl.CTIPlatformItegrationExampleTests;
import br.com.omniplusoft.gateway.application.ctiplatform.support.StompTextMessageBuilder;
import br.com.omniplusoft.gateway.application.ctiplatform.support.TestClientWebSocketHandler;
import br.com.omniplusoft.gateway.domain.ctiplatform.event.CTIEvent;
import br.com.omniplusoft.gateway.application.ctiplatform.support.WebSocketTestServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.socket.TextMessage;
import br.com.omniplusoft.gateway.application.ctiplatform.support.TomcatWebSocketTestServer;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by hermeswaldemarin on 14/12/15.
 */
public abstract class AbstractCTIPlatformTest {

    protected static final Log logger = LogFactory.getLog(CTIPlatformItegrationExampleTests.class);

    protected WebSocketTestServer server;

    protected AnnotationConfigWebApplicationContext wac;

    protected static long TIMEOUT = 2;

    protected String activeProfile = "example";


    @Before
    public void setUp() throws Exception {

        System.setProperty("spring.profiles.active", activeProfile);

        this.wac = new AnnotationConfigWebApplicationContext();
        this.wac.register(TestWebSocketConfig.class);
        this.wac.refresh();

        this.server = new TomcatWebSocketTestServer(new org.springframework.web.socket.client.standard.StandardWebSocketClient());
        this.server.setup();
        this.server.deployConfig(this.wac);
        this.server.start();
    }


    @After
    public void tearDown() throws Exception {
        try {
            this.server.undeployConfig();
        }
        catch (Throwable t) {
            logger.error("Failed to undeploy application config", t);
        }
        try {
            this.server.stop();
        }
        catch (Throwable t) {
            logger.error("Failed to stop server", t);
        }
        try {
            this.wac.close();
        }
        catch (Throwable t) {
            logger.error("Failed to close WebApplicationContext", t);
        }
    }

    protected String getPayloadReturnFromEvent(String eventName, CTIEvent eventToSend) throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        TextMessage messageSubscribe = StompTextMessageBuilder.create(StompCommand.SUBSCRIBE)
                .headers("id:subs1", "destination:/messages/cti/eventcallback").build();

        TextMessage messageSend = StompTextMessageBuilder.create(StompCommand.SEND)
                .headers("destination:/gateway/cti/command/" + eventName).body(mapper.writeValueAsString(eventToSend)).build();

        TestClientWebSocketHandler webSocketHandler = new TestClientWebSocketHandler(1, messageSend, messageSubscribe);
        this.server.doHandshake(webSocketHandler, "/sockjs").get();

        assertTrue(webSocketHandler.latch.await(TIMEOUT, TimeUnit.SECONDS));

        return webSocketHandler.actual.get(0).getPayload();
    }


    protected String getPayloadReturnFromEvent(String eventName) throws Exception{
        return getPayloadReturnFromEvent(eventName, new CTIEvent(new HashMap<>()));
    }

}
