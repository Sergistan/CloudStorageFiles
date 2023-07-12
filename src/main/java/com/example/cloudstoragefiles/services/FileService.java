package com.example.cloudstoragefiles.services;

import com.example.cloudstoragefiles.exceptions.ErrorDeleteFile;
import com.example.cloudstoragefiles.exceptions.ErrorInputData;
import com.example.cloudstoragefiles.exceptions.ErrorUnauthorized;
import com.example.cloudstoragefiles.models.File;
import com.example.cloudstoragefiles.models.User;
import com.example.cloudstoragefiles.repositories.AuthRepository;
import com.example.cloudstoragefiles.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class FileService {

    private final AuthRepository authRepository;
    private final FileRepository fileRepository;

    @Autowired
    public FileService(AuthRepository authRepository, FileRepository fileRepository) {
        this.authRepository = authRepository;
        this.fileRepository = fileRepository;
    }

    public boolean uploadFile(String authToken, String filename, MultipartFile multipartFile) {
        User user = getUserByToken(authToken);
        if (user == null) {
            throw new ErrorUnauthorized();
        }
        try {
            File uploadFile = new File(filename, LocalDateTime.now(), multipartFile.getSize(), multipartFile.getBytes());
            fileRepository.save(uploadFile);
            System.out.println(fileRepository.findAll());
            return true;
        } catch (IOException e) {
            throw new ErrorInputData();
        }
    }

    public void deleteFile(String authToken, String filename) {
        User user = getUserByToken(authToken);
        if (user == null) {
            throw new ErrorUnauthorized();
        }
        fileRepository.deleteByFilename(filename);
        File deleteFile = fileRepository.findByFilename(filename);
        if (deleteFile != null) {
            throw new ErrorDeleteFile();
        }


    }

    public User getUserByToken(String authToken) {
        if (authToken.startsWith("Bearer ")) {
            String tokenWithoutBearer = authToken.substring(7);
            return authRepository.getUserByToken(tokenWithoutBearer);
        } else return null;
    }


}
