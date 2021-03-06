package br.com.plusoftomni.integration.application.telephonyplatform.exampleimpl;

import br.com.plusoftomni.integration.OmniIntegrationPlatformApplication;
import br.com.plusoftomni.integration.domain.telephonyplatform.event.ReadyEvent;
import br.com.plusoftomni.integration.domain.telephonyplatform.CTIEventHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hermeswaldemarin on 14/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OmniIntegrationPlatformApplication.class)
@ActiveProfiles("example")
public class ReadyHandlerTest {

    ReadyEvent event;
    private boolean called;

    @Autowired
    private CTIEventHandler handler;

    @Before
    public void init(){

        event = Mockito.mock(ReadyEvent.class);

        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                called = true;
                return null;
            }
        }).when(event).getArguments();


    }

    @Test
    public void shouldReceiveALoginEvent(){

        handler.dispatch(event);

        Assert.assertTrue(called);

    }

}
