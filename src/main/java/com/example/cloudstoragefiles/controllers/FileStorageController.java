package com.example.cloudstoragefiles.controllers;

import com.example.cloudstoragefiles.models.request.RequestEditFileName;
import com.example.cloudstoragefiles.models.response.ResponseFile;
import com.example.cloudstoragefiles.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping("/file")
    public ResponseEntity<?> downloadFile (@RequestHeader("auth-token") String authToken, @RequestParam ("filename") String filename) {
        byte [] file = fileService.downloadFile(authToken, filename);
        return ResponseEntity.ok(file);
    }

    @PutMapping("/file")
    public ResponseEntity<String> editFile (@RequestHeader("auth-token") String authToken, @RequestParam ("filename") String filename, @RequestBody RequestEditFileName requestEditFileName) {
        fileService.editFile(authToken, filename, requestEditFileName);
        return ResponseEntity.ok("Edit file name");
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseFile>> getAllFiles (@RequestHeader("auth-token") String authToken, @RequestParam ("limit") Integer limit) {
       List<ResponseFile> rp = fileService.getAllFiles(authToken, limit);
        return ResponseEntity.ok(rp);
    }

}
