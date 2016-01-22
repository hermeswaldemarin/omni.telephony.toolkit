package br.com.omniplusoft.gateway.application.ctiplatform.support;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class TestClientWebSocketHandler extends TextWebSocketHandler {

        private final TextMessage[] messagesToSend;

        private final int expected;

        public final List<TextMessage> actual = new CopyOnWriteArrayList<>();

        public final CountDownLatch latch;

        public TestClientWebSocketHandler(int expectedNumberOfMessages, TextMessage... messagesToSend) {
            this.messagesToSend = messagesToSend;
            this.expected = expectedNumberOfMessages;
            this.latch = new CountDownLatch(this.expected);
        }

        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            for (TextMessage message : this.messagesToSend) {
                session.sendMessage(message);
            }
        }

        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            this.actual.add(message);
            this.latch.countDown();
        }
    }