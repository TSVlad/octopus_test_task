package ru.tsvlad.octopus.restapi.dto.mapper;

import org.mapstruct.Mapper;
import ru.tsvlad.octopus.common.UploadResult;
import ru.tsvlad.octopus.restapi.dto.UploadResultDto;

@Mapper(componentModel = "spring", uses = {FileInfoMapper.class, UploadErrorInfoMapper.class})
public interface UploadResultMapper {
    UploadResultDto toDto(UploadResult uploadResult);
}
