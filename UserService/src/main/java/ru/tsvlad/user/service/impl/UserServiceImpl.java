package ru.tsvlad.user.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tsvlad.user.common.RegistrationObject;
import ru.tsvlad.user.common.User;
import ru.tsvlad.user.common.mapper.KeycloakMapper;
import ru.tsvlad.user.service.KeycloakService;
import ru.tsvlad.user.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final KeycloakService keycloakService;

    private final KeycloakMapper keycloakMapper;

    @Override
    public User registerUser(RegistrationObject registrationObject) {
        return keycloakMapper.representationToUser(keycloakService.addUser(registrationObject));
    }
}
