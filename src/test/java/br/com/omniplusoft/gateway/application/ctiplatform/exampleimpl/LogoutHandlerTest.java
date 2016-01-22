package br.com.omniplusoft.gateway.application.ctiplatform.exampleimpl;

import br.com.omniplusoft.gateway.OmniPlusoftGatewayPlatformApplication;
import br.com.omniplusoft.gateway.domain.ctiplatform.CTIEventHandler;
import br.com.omniplusoft.gateway.domain.ctiplatform.event.LogoutEvent;
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
@SpringApplicationConfiguration(classes = OmniPlusoftGatewayPlatformApplication.class)
@ActiveProfiles("example")
public class LogoutHandlerTest {

    LogoutEvent logoutEvent;
    private boolean called;

    @Autowired
    private CTIEventHandler handler;

    @Before
    public void init(){

        logoutEvent = Mockito.mock(LogoutEvent.class);

        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                called = true;
                return null;
            }
        }).when(logoutEvent).getArguments();


    }

    @Test
    public void shouldReceiveALoginEvent(){

        handler.dispatch(logoutEvent);

        Assert.assertTrue(called);

    }

}