package ru.tsvlad.octopus.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UploadResult {
    List<FileInfo> uploadedFiles;
    List<UploadErrorInfo> failedFiles;
}
