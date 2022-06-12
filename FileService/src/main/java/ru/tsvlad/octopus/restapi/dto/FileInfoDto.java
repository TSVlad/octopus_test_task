package ru.tsvlad.octopus.restapi.dto;

import lombok.Data;

@Data
public class FileInfoDto {
    private String id;
    private String ownerId;
    private String fileName;
}
