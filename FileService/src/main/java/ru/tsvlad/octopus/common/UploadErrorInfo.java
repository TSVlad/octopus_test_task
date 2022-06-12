package ru.tsvlad.octopus.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadErrorInfo {
    private String fileName;
    private String reason;
}
