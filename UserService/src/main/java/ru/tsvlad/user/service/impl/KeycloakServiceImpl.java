package ru.tsvlad.user.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.tsvlad.user.common.RegistrationObject;
import ru.tsvlad.user.config.props.KeycloakProperties;
import ru.tsvlad.user.restapi.controller.advice.exceptions.ConflictException;
import ru.tsvlad.user.service.KeycloakService;

import javax.ws.rs.core.Response;

@Service
@AllArgsConstructor
@Slf4j
public class KeycloakServiceImpl implements KeycloakService {

    private final Keycloak keycloak;
    private final KeycloakProperties keycloakProperties;

    @Override
    public UserRepresentation addUser(RegistrationObject registrationObject) {
        UserRepresentation userRepresentation = createRepresentation(registrationObject);

        Response response = realm().users().create(userRepresentation);
        String id;
        if (response.getStatus() == HttpStatus.CREATED.value()) {
            id = getUserIdFromCreatedResponse(response);
        } else if (response.getStatus() == HttpStatus.CONFLICT.value()) {
            throw new ConflictException("Username is already in use");
        } else {
            log.warn("Unhandled response status {}", response.getStatus());
            throw new RuntimeException("Error while creating user in keycloak: " + response.getStatus());
        }
        UserResource userResource = realm().users().get(id);
        userResource.resetPassword(createPasswordCredentials(registrationObject.getPassword()));

        return userResource.toRepresentation();
    }

    private UserRepresentation createRepresentation(RegistrationObject registrationObject) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(registrationObject.getUsername());
        userRepresentation.setEnabled(true);
        return userRepresentation;
    }

    private CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setValue(password);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        return credentialRepresentation;
    }

    private String getUserIdFromCreatedResponse(Response response) {
        if (response.getStatus() != 201) {
            throw new IllegalArgumentException("Impossible to get user's id: keycloak response status != 201!");
        }
        String[] location = response.getHeaderString("Location").split("/");
        return location[location.length - 1];
    }

    private RealmResource realm() {
        return keycloak.realm(keycloakProperties.getRealm());
    }
}
