package ru.tsvlad.octopus.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tsvlad.octopus.db.entity.FileEntity;

import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, String> {
    List<FileEntity> findAllByOwnerId(String ownerId);
}
