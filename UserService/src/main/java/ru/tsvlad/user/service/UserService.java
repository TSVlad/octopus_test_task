package ru.tsvlad.user.service;

import ru.tsvlad.user.common.RegistrationObject;
import ru.tsvlad.user.common.User;

public interface UserService {
    User registerUser(RegistrationObject registrationObject);
}
