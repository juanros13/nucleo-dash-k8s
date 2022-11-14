package com.nucleo.dash.msvc.user.configuration;

import com.nucleo.dash.msvc.user.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KeycloakManager {

    private final KeycloakProperties keycloakProperties;

    public RealmResource getKeyCloakInstanceWithRealm() {
        try {
            return keycloakProperties.getInstance().realm(keycloakProperties.getRealm());
        } catch (Exception e) {
            log.info("Entrando 7 {}", e.getCause());
            log.info("Entrando 7 {}", e.getMessage());
            log.info("Entrando 7 {}", e.getStackTrace());
            throw new EntityNotFoundException("Problemas con keycloak");
        }

    }

}
