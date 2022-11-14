package com.nucleo.dash.msvc.user.configuration;

import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KeycloakProperties {
    @Value("${app.config.keycloak.server-url}")
    private String serverUrl;

    @Value("${app.config.keycloak.realm}")
    private String realm;

    @Value("${app.config.keycloak.clientId}")
    private String clientId;

    @Value("${app.config.keycloak.client-secret}")
    private String clientSecret;

    @Value("${app.config.keycloak.user}")
    private String user;

    @Value("${app.config.keycloak.pass}")
    private String pass;

    private static Keycloak keycloakInstance = null;

    public Keycloak getInstance() {
        log.info("url {}", serverUrl);
        log.info("realm {}", realm);
        log.info("grantType {}", OAuth2Constants.PASSWORD);
        log.info("clientId {}", clientId);
        log.info("user {}", user);
        log.info("pass {}", pass);

        if (keycloakInstance == null) {
            keycloakInstance = KeycloakBuilder
                    .builder()
                    .serverUrl(serverUrl)
                    .realm("master")
                    .grantType(OAuth2Constants.PASSWORD)
                    .clientId(clientId)
                    .username(user)
                    .password(pass)
                    .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                    .build();
        }
        return keycloakInstance;
    }

    public String getRealm() {
        return realm;
    }
}
