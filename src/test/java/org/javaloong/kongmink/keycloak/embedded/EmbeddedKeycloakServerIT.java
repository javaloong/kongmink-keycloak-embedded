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

import com.github.mjeanroy.junit.servers.annotations.TestServerConfiguration;
import com.github.mjeanroy.junit.servers.jetty.EmbeddedJetty;
import com.github.mjeanroy.junit.servers.jetty.EmbeddedJettyConfiguration;
import com.github.mjeanroy.junit.servers.jetty.jupiter.JettyServerExtension;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(JettyServerExtension.class)
public class EmbeddedKeycloakServerIT {

    @TestServerConfiguration
    private static EmbeddedJettyConfiguration configuration = createJettyConfiguration();

    @Test
    public void testEmbeddedServer(EmbeddedJetty jetty) throws Exception {
        String url = jetty.getUrl() + "auth";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        assertThat(response).isNotNull();
        assertThat(response.code()).isEqualTo(200);
    }

    private static EmbeddedJettyConfiguration createJettyConfiguration() {
        return EmbeddedJettyConfiguration.builder()
                .withOverrideDescriptor("src/test/resources/web.xml")
                .build();
    }
}
