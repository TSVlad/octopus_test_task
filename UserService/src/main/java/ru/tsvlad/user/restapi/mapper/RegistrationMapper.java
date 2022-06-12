package ru.tsvlad.user.restapi.mapper;

import org.mapstruct.Mapper;
import ru.tsvlad.user.common.RegistrationObject;
import ru.tsvlad.user.restapi.dto.RegistrationDto;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    RegistrationObject toObject(RegistrationDto registrationDto);
}
