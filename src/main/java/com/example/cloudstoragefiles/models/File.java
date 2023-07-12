package com.example.cloudstoragefiles.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String filename;

    @Column
    private LocalDateTime editedAt;

    @Column
    private Long size;

    @Lob
    @Column
    private byte[] fileContent;

    public File(String filename, LocalDateTime editedAt, Long size, byte[] fileContent) {
        this.filename = filename;
        this.editedAt = editedAt;
        this.size = size;
        this.fileContent = fileContent;
    }
}
