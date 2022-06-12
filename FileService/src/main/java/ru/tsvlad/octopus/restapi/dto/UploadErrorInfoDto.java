package ru.tsvlad.octopus.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadErrorInfoDto {
    private String fileName;
    private String reason;
}
