package com.example.cloudstoragefiles.controllers;

import com.example.cloudstoragefiles.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileStorageController {

    private final FileService fileService;

    @Autowired
    public FileStorageController(FileService fileService) {
        this.fileService = fileService;
    }


    @PostMapping("/file")
    public ResponseEntity<String> uploadFile (@RequestHeader("auth-token") String authToken, @RequestParam ("filename") String filename, MultipartFile file) {
        fileService.uploadFile(authToken, filename, file);
        return ResponseEntity.ok("Success upload");
    }

    @DeleteMapping("/file")
    public ResponseEntity<String> deleteFile (@RequestHeader("auth-token") String authToken, @RequestParam ("filename") String filename) {
        fileService.deleteFile(authToken, filename);
        return ResponseEntity.ok("Success delete");
    }


}
