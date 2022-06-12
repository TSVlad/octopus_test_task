package ru.tsvlad.user.service;

import org.keycloak.representations.idm.UserRepresentation;
import ru.tsvlad.user.common.RegistrationObject;

public interface KeycloakService {
    UserRepresentation addUser(RegistrationObject registrationObject);
}
