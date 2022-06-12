package ru.tsvlad.user.restapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationDto {
    @NotNull(message = "Field 'username' must be specified")
    @NotBlank(message = "Field 'username' must not be empty")
    private String username;

    @NotNull(message = "Field 'password' must be specified")
    @Size(min = 8, message = "Minimum password length is 8")
    private String password;
}
