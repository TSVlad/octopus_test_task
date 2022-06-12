package ru.tsvlad.octopus.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    String getUserId(Authentication authentication);
}
