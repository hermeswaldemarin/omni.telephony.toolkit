package br.com.omniplusoft.gateway.application.ctiplatform.exampleimpl;

import br.com.omniplusoft.gateway.application.ctiplatform.AbstractCTIPlatformTest;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CTIPlatformItegrationExampleTests extends AbstractCTIPlatformTest {

    public CTIPlatformItegrationExampleTests(){
        this.activeProfile = "example";
    }

    @Test
    public void loginShouldReturnOkMessage() throws Exception {

        assertTrue(getPayloadReturnFromEvent("login").contains("Login OK"));

    }

    @Test
    public void logoutShouldReturnOkMessage() throws Exception {

        assertTrue(getPayloadReturnFromEvent("logout").contains("Logout OK"));

    }

    @Test
    public void readyShouldReturnOkMessage() throws Exception {

        assertTrue(getPayloadReturnFromEvent("ready").contains("Now you are available"));

    }

    @Test
    public void notReadyShouldReturnOkMessage() throws Exception {

        assertTrue(getPayloadReturnFromEvent("notReady").contains("Now you are unavailable"));

    }

    @Test
    public void makeCallShouldReturnOkMessage() throws Exception {

        assertTrue(getPayloadReturnFromEvent("makeCall").contains("Call complete"));

    }

    @Test
    public void answerShouldReturnOkMessage() throws Exception {

        assertTrue(getPayloadReturnFromEvent("answer").contains("Speaking"));

    }

    @Test
    public void dropCallShouldReturnOkMessage() throws Exception {

        assertTrue(getPayloadReturnFromEvent("dropCall").contains("Call dropped."));

    }

    @Test
    public void holdShouldReturnOkMessage() throws Exception {

        assertTrue(getPayloadReturnFromEvent("hold").contains("Holding"));

    }

    @Test
    public void unHoldShouldReturnOkMessage() throws Exception {

        assertTrue(getPayloadReturnFromEvent("unHold").contains("Unhold call"));

    }

    @Test
    public void consultShouldReturnOkMessage() throws Exception {

        assertTrue(getPayloadReturnFromEvent("consult").contains("Consult completed"));

    }

    @Test
    public void cancelConsultShouldReturnOkMessage() throws Exception {

        assertTrue(getPayloadReturnFromEvent("cancelConsult").contains("Consult canceled"));

    }

    @Test
    public void transferShouldReturnOkMessage() throws Exception {

        assertTrue(getPayloadReturnFromEvent("transfer").contains("Transfer Completed"));

    }

    @Test
    public void conferenceShouldReturnOkMessage() throws Exception {

        assertTrue(getPayloadReturnFromEvent("conference").contains("Conference Complete"));

    }

    @Test
    public void genericEventShouldReturnOkMessage() throws Exception {

        assertTrue(getPayloadReturnFromEvent("genericEvent").contains("genericEvent called"));

    }
}
