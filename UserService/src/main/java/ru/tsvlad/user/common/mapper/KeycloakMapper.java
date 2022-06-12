package ru.tsvlad.user.common.mapper;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;
import ru.tsvlad.user.common.User;

@Component
public class KeycloakMapper {
    public User representationToUser(UserRepresentation userRepresentation) {
        return User.builder()
                .id(userRepresentation.getId())
                .username(userRepresentation.getUsername())
                .build();
    }
}
