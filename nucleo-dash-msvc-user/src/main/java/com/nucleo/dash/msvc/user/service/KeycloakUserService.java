package com.nucleo.dash.msvc.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import com.nucleo.dash.msvc.user.configuration.KeycloakManager;
import com.nucleo.dash.msvc.user.exception.EntityNotFoundException;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakUserService {

    private final KeycloakManager keyCloakManager;

    public Integer createUser(UserRepresentation userRepresentation) {
        Response response = keyCloakManager.getKeyCloakInstanceWithRealm().users().create(userRepresentation);
        return response.getStatus();
    }

    public void updateUser(UserRepresentation userRepresentation) {
        keyCloakManager.getKeyCloakInstanceWithRealm().users().get(userRepresentation.getId()).update(userRepresentation);
    }


    public List<UserRepresentation> readUserByEmail(String email) {
        log.info("Entrando 6");
        try {
            return keyCloakManager.getKeyCloakInstanceWithRealm().users().search(email);
        } catch (Exception e) {
            log.info("Entrando 6 {}", e.getCause());
            log.info("Entrando 6 {}", e.getMessage());
            log.info("Entrando 6 {}", e.getStackTrace());
            throw new EntityNotFoundException("Problemas con keycloak");
        }
        
    }
    public UserRepresentation getByEmail(String email) {
        List<UserRepresentation> userRepresentations = keyCloakManager.getKeyCloakInstanceWithRealm().users().search(email);
        if(userRepresentations.size() >0){
            return userRepresentations.get(0);
        }
        return null;
    }
    public void credentialRest(UserRepresentation userRepresentation){
        List actions = new LinkedList();
        actions.add("UPDATE_PASSWORD");
        keyCloakManager.getKeyCloakInstanceWithRealm().users().get(userRepresentation.getId()).executeActionsEmail(actions);

    }
    public void emailVerified(UserRepresentation userRepresentation){
        List actions = new LinkedList();
        actions.add("VERIFY_EMAIL");
        keyCloakManager.getKeyCloakInstanceWithRealm().users().get(userRepresentation.getId()).executeActionsEmail(actions);

    }
    public UserRepresentation readUser(String authId) {
        try {
            UserResource userResource = keyCloakManager.getKeyCloakInstanceWithRealm().users().get(authId);
            return userResource.toRepresentation();
        } catch (Exception e) {
            throw new EntityNotFoundException("User not found under given ID");
        }
    }


    public UserRepresentation changePassword(String authId, String newPassword) {
        try {
            UserResource userResource = keyCloakManager.getKeyCloakInstanceWithRealm().users().get(authId);

            CredentialRepresentation creds = new CredentialRepresentation();
            creds.setType("password");
            creds.setValue(newPassword);
            creds.setTemporary(false);
            userResource.resetPassword(creds);
            return userResource.toRepresentation();
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info(e.getStackTrace().toString());
            throw new EntityNotFoundException("User not found under given ID");
        }
    }
}
