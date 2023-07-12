package com.example.cloudstoragefiles.repositories;

import com.example.cloudstoragefiles.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {

    void deleteByFilename(String filename);

     File findByFilename(String filename);
}
