/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.javaloong.kongmink.keycloak.embedded;

import org.keycloak.common.ClientConnection;
import org.keycloak.services.filters.AbstractRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EmbeddedKeycloakRequestFilter extends AbstractRequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        servletRequest.setCharacterEncoding("UTF-8");
        ClientConnection clientConnection = createConnection((HttpServletRequest) servletRequest);

        filter(clientConnection, (session) -> {
            try {
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void destroy() {

    }

    private ClientConnection createConnection(HttpServletRequest request) {
        return new ClientConnection() {
            @Override
            public String getRemoteAddr() {
                return request.getRemoteAddr();
            }

            @Override
            public String getRemoteHost() {
                return request.getRemoteHost();
            }

            @Override
            public int getRemotePort() {
                return request.getRemotePort();
            }

            @Override
            public String getLocalAddr() {
                return request.getLocalAddr();
            }

            @Override
            public int getLocalPort() {
                return request.getLocalPort();
            }
        };
    }
}
