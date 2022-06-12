package ru.tsvlad.octopus.restapi.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.tsvlad.octopus.restapi.dto.FileInfoDto;
import ru.tsvlad.octopus.restapi.dto.UploadResultDto;
import ru.tsvlad.octopus.restapi.dto.mapper.FileInfoMapper;
import ru.tsvlad.octopus.restapi.dto.mapper.UploadResultMapper;
import ru.tsvlad.octopus.service.AuthenticationService;
import ru.tsvlad.octopus.service.FileService;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
@Slf4j
public class FileController {

    private final FileService fileService;
    private final AuthenticationService authenticationService;

    private final UploadResultMapper uploadResultMapper;
    private final FileInfoMapper fileInfoMapper;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public UploadResultDto uploadFiles(@RequestPart("file") @NotEmpty(message = "File(s) must be specified")
                                                   List<MultipartFile> files, Authentication authentication) {
        String userId = authenticationService.getUserId(authentication);
        log.info("Upload files {} request from user with id {}", files.stream().map(MultipartFile::getOriginalFilename)
                .collect(Collectors.toList()), userId);
        return uploadResultMapper.toDto(fileService.uploadFiles(files, userId));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<FileInfoDto> getAllFilesInfo(Authentication authentication) {
        String userId = authenticationService.getUserId(authentication);
        log.info("Get all files info called for user with id {}", userId);
        return fileService.getAllFiles(userId).stream().map(fileInfoMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}/link")
    @PreAuthorize("isAuthenticated()")
    public String getFileLink(@PathVariable String id, Authentication authentication) {
        String userId = authenticationService.getUserId(authentication);
        log.info("Get link request got for file id {} from user with id {}", id, userId);
        return fileService.getLinkToFile(id, userId);
    }
}
