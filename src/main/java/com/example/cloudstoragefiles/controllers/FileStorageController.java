package com.example.cloudstoragefiles.controllers;

import com.example.cloudstoragefiles.models.request.RequestEditFileName;
import com.example.cloudstoragefiles.models.response.ResponseFile;
import com.example.cloudstoragefiles.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> uploadFile(@RequestHeader("auth-token") String authToken, @RequestParam("filename") String filename, MultipartFile file) {
        fileService.uploadFile(authToken, filename, file);
        return new ResponseEntity<>("Success upload", HttpStatus.OK);
    }

    @DeleteMapping("/file")
    public ResponseEntity<?> deleteFile(@RequestHeader("auth-token") String authToken, @RequestParam("filename") String filename) {
        fileService.deleteFile(authToken, filename);
        return new ResponseEntity<>("Success delete", HttpStatus.OK);
    }

    @GetMapping("/file")
    public ResponseEntity<?> downloadFile(@RequestHeader("auth-token") String authToken, @RequestParam("filename") String filename) {
        byte[] file = fileService.downloadFile(authToken, filename);
        return new ResponseEntity<>(file, HttpStatus.OK);
    }

    @PutMapping("/file")
    public ResponseEntity<?> editFile(@RequestHeader("auth-token") String authToken, @RequestParam("filename") String filename, @RequestBody RequestEditFileName requestEditFileName) {
        fileService.editFile(authToken, filename, requestEditFileName);
        return new ResponseEntity<>("Edit file name", HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllFiles(@RequestHeader("auth-token") String authToken, @RequestParam("limit") Integer limit) {
        List<ResponseFile> rp = fileService.getAllFiles(authToken, limit);
        return new ResponseEntity<>(rp, HttpStatus.OK);
    }

}
