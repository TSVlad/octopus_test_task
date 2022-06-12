package ru.tsvlad.octopus.common.mapper;

import org.mapstruct.Mapper;
import ru.tsvlad.octopus.common.FileInfo;
import ru.tsvlad.octopus.db.entity.FileEntity;

@Mapper(componentModel = "spring")
public interface FileMapper {

    FileInfo toFileInfo(FileEntity fileEntity);
}
