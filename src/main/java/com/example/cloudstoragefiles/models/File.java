package com.example.cloudstoragefiles.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column (name = "filename")
    private String filename;

    @Column (name = "edited_at")
    private LocalDateTime editedAt;

    @Column (name = "size")
    private Long size;

    @Column (name = "file_content")
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
