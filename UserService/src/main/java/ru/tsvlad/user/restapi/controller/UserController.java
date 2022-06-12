package ru.tsvlad.user.restapi.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tsvlad.user.restapi.dto.RegistrationDto;
import ru.tsvlad.user.restapi.dto.UserDto;
import ru.tsvlad.user.restapi.mapper.RegistrationMapper;
import ru.tsvlad.user.restapi.mapper.UserMapper;
import ru.tsvlad.user.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;
    private final RegistrationMapper registrationMapper;

    @PostMapping
    public UserDto register(@Valid @RequestBody RegistrationDto registrationDto) {
        log.info("New registration request for username {}", registrationDto.getUsername());
        return userMapper.toDto(userService.registerUser(registrationMapper.toObject(registrationDto)));
    }
}
