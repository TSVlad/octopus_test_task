package ru.tsvlad.octopus.service;

import org.springframework.web.multipart.MultipartFile;
import ru.tsvlad.octopus.common.FileInfo;
import ru.tsvlad.octopus.common.UploadResult;

import java.util.List;

public interface FileService {
    UploadResult uploadFiles(List<MultipartFile> files, String userId);
    List<FileInfo> getAllFiles(String userId);
    String getLinkToFile(String fileId, String userId);
}
