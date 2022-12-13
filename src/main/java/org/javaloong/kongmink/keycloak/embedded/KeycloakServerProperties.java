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

public class KeycloakServerProperties {

    public static final String SERVER_CONTEXT_PATH = "keycloak.embedded.server.context-path";
    public static final String REALM_CONFIGURATION_PATH = "keycloak.embedded.realm.configuration.path";
    public static final String ADMIN_USERNAME = "keycloak.embedded.security.admin.username";
    public static final String ADMIN_PASSWORD = "keycloak.embedded.security.admin.password";

    private final String contextPath;
    private final String realmConfigPath;
    private final String adminUser;
    private final String adminPassword;

    public KeycloakServerProperties() {
        this.contextPath = System.getProperty(SERVER_CONTEXT_PATH, "/auth");
        this.realmConfigPath = System.getProperty(REALM_CONFIGURATION_PATH, "demo-realm.json");
        this.adminUser = System.getProperty(ADMIN_USERNAME, "admin");
        this.adminPassword = System.getProperty(ADMIN_PASSWORD, "admin");
    }

    public KeycloakServerProperties(String contextPath, String realmConfigPath,
                                    String adminUser, String adminPassword) {
        this.contextPath = contextPath;
        this.realmConfigPath = realmConfigPath;
        this.adminUser = adminUser;
        this.adminPassword = adminPassword;
    }

    public String getContextPath() {
        return contextPath;
    }

    public String getRealmConfigPath() {
        return realmConfigPath;
    }

    public String getAdminUser() {
        return adminUser;
    }

    public String getAdminPassword() {
        return adminPassword;
    }
}
