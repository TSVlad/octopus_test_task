package ru.tsvlad.octopus.service.impl;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.tsvlad.octopus.common.FileInfo;
import ru.tsvlad.octopus.common.UploadErrorInfo;
import ru.tsvlad.octopus.common.UploadResult;
import ru.tsvlad.octopus.common.mapper.FileMapper;
import ru.tsvlad.octopus.config.props.ObjectStorageProperties;
import ru.tsvlad.octopus.db.entity.FileEntity;
import ru.tsvlad.octopus.db.repository.FileRepository;
import ru.tsvlad.octopus.exceptions.ForbiddenException;
import ru.tsvlad.octopus.exceptions.NotFoundException;
import ru.tsvlad.octopus.service.FileService;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    private final AmazonS3 amazonS3;

    private final ObjectStorageProperties objectStorageProperties;

    private final FileMapper fileInfoMapper;


    @Override
    public UploadResult uploadFiles(List<MultipartFile> files, String userId) {
        List<FileInfo> uploadedImages = new ArrayList<>();
        List<UploadErrorInfo> uploadErrorInfos = new ArrayList<>();

        for (MultipartFile file : files) {
            ObjectMetadata objectMetadata = getMetadataFromMultipartFile(file);
            String id = UUID.randomUUID().toString();

            try {
                amazonS3.putObject(objectStorageProperties.getBucketName(),
                        getFileKey(userId, id, file.getOriginalFilename()),
                        file.getInputStream(), objectMetadata);
            } catch (IOException e) {
                uploadErrorInfos.add(new UploadErrorInfo(file.getOriginalFilename(), "Bad file"));
            } catch (SdkClientException e) {
                uploadErrorInfos.add(new UploadErrorInfo(file.getOriginalFilename(), "Problem with connecting to storage"));
            }

            FileEntity fileEntity = fileRepository.save(FileEntity.builder().id(id).fileName(file.getOriginalFilename())
                    .ownerId(userId).build());
            uploadedImages.add(fileInfoMapper.toFileInfo(fileEntity));
        }


        return new UploadResult(uploadedImages, uploadErrorInfos);
    }

    @Override
    public List<FileInfo> getAllFiles(String userId) {
        return fileRepository.findAllByOwnerId(userId).stream().map(fileInfoMapper::toFileInfo).collect(Collectors.toList());
    }

    @Override
    public String getLinkToFile(String fileId, String userId) {
        Optional<FileEntity> fileEntityOptional = fileRepository.findById(fileId);
        if (fileEntityOptional.isEmpty()) {
            throw new NotFoundException("File not found");
        }
        FileEntity fileEntity = fileEntityOptional.get();
        if (!fileEntity.getOwnerId().equals(userId)) {
            throw new ForbiddenException("Permission denied");
        }
        return amazonS3.generatePresignedUrl(objectStorageProperties.getBucketName(),
                        getFileKey(fileEntity.getOwnerId(), fileEntity.getId(), fileEntity.getFileName()),
                        Date.from(ZonedDateTime.now().plus(objectStorageProperties.getUrlDuration(), ChronoUnit.SECONDS)
                                .toInstant()))
                .toString();
    }

    private ObjectMetadata getMetadataFromMultipartFile(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        return objectMetadata;
    }

    private String getFileKey(String userId, String fileId, String fileName) {
        return userId + "/" + fileId + "/" + fileName;
    }
}
