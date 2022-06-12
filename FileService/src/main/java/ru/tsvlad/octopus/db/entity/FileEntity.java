package ru.tsvlad.octopus.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "file_name")
    private String fileName;
}
