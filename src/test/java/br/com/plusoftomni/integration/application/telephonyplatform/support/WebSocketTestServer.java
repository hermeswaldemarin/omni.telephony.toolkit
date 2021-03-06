/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.plusoftomni.integration.application.telephonyplatform.support;

import javax.servlet.Filter;
import javax.servlet.ServletContext;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;

/**
 * Contract for a test server to use for WebSocket integration tests.
 *
 * @author Rossen Stoyanchev
 * @author Sam Brannen
 */
public interface WebSocketTestServer {

    int getPort();

    void setup();

    void deployConfig(WebApplicationContext cxt, Filter... filters);

    /**
     * Get the {@link ServletContext} created by the underlying server.
     *
     * <p>The {@code ServletContext} is only guaranteed to be available
     * after {@link #deployConfig} has been invoked.
     *
     * @since 4.2
     */
    ServletContext getServletContext();

    void undeployConfig();

    void start() throws Exception;

    void stop() throws Exception;

    public ListenableFuture<WebSocketSession> doHandshake(WebSocketHandler clientHandler, String endpointPath);

}