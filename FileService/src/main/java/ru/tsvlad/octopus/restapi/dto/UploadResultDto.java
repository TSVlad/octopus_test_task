package ru.tsvlad.octopus.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UploadResultDto {
    List<FileInfoDto> uploadedFiles;
    List<UploadErrorInfoDto> failedFiles;
}
