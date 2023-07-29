package com.example.cloudstoragefiles.repositories;

import com.example.cloudstoragefiles.models.File;
import com.example.cloudstoragefiles.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    @Modifying
    @Query("DELETE FROM File f WHERE f.user = ?1 AND f.filename = ?2")
    int deleteByUserAndFilename(User user, String filename);

    List<File> findAllByUser(User user);

    File findByUserAndFilename(User user, String filename);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE File f SET f.filename = ?1 WHERE f.user = ?2 AND f.filename = ?3")
    int setNewFilenameByUserAndFilename(String newFilename, User user, String filename);
}
