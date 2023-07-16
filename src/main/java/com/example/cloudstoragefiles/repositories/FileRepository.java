package com.example.cloudstoragefiles.repositories;

import com.example.cloudstoragefiles.models.File;
import com.example.cloudstoragefiles.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FileRepository extends JpaRepository<File, Long> {
    @Modifying
    @Query("DELETE FROM File f WHERE f.user = ?1 AND f.filename = ?2")
    int deleteByUserAndFilename(User user, String filename);
}
