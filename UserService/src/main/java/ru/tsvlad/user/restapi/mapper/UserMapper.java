package ru.tsvlad.user.restapi.mapper;

import org.mapstruct.Mapper;
import ru.tsvlad.user.common.User;
import ru.tsvlad.user.restapi.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
