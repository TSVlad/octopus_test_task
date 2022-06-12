package ru.tsvlad.octopus.restapi.dto.mapper;

import org.mapstruct.Mapper;
import ru.tsvlad.octopus.common.UploadErrorInfo;
import ru.tsvlad.octopus.restapi.dto.UploadErrorInfoDto;

@Mapper(componentModel = "spring")
public interface UploadErrorInfoMapper {
    UploadErrorInfoDto toDto(UploadErrorInfo uploadErrorInfo);
}
