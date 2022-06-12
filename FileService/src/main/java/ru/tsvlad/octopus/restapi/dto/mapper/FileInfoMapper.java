package ru.tsvlad.octopus.restapi.dto.mapper;

import org.mapstruct.Mapper;
import ru.tsvlad.octopus.common.FileInfo;
import ru.tsvlad.octopus.restapi.dto.FileInfoDto;

@Mapper(componentModel = "spring")
public interface FileInfoMapper {
    FileInfoDto toDto(FileInfo fileInfo);
}
