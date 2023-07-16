package com.example.cloudstoragefiles.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "filename", unique = true)
    @NotNull
    private String filename;

    @Column (name = "edited_at")
    private LocalDateTime editedAt;

    @Column (name = "size")
    @NotNull
    private Long size;

    @Column (name = "file_content")
    @NotNull
    private byte[] fileContent;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public File(String filename, LocalDateTime editedAt, long size, byte[] fileContent, User user) {
        this.filename = filename;
        this.editedAt = editedAt;
        this.size = size;
        this.fileContent = fileContent;
        this.user = user;
    }
}
