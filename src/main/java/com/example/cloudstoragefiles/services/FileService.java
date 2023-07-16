package com.example.cloudstoragefiles.services;

import com.example.cloudstoragefiles.exceptions.ErrorDeleteFile;
import com.example.cloudstoragefiles.exceptions.ErrorInputData;
import com.example.cloudstoragefiles.exceptions.ErrorUnauthorized;
import com.example.cloudstoragefiles.models.File;
import com.example.cloudstoragefiles.models.User;
import com.example.cloudstoragefiles.models.request.RequestEditFileName;
import com.example.cloudstoragefiles.models.response.ResponseFile;
import com.example.cloudstoragefiles.repositories.AuthRepository;
import com.example.cloudstoragefiles.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileService {

    private final AuthRepository authRepository;
    private final FileRepository fileRepository;

    @Autowired
    public FileService(AuthRepository authRepository, FileRepository fileRepository) {
        this.authRepository = authRepository;
        this.fileRepository = fileRepository;
    }

    @Transactional
    public void uploadFile(String authToken, String filename, MultipartFile multipartFile) {
        User user = getUserByToken(authToken);
        if (user == null) {
            throw new ErrorUnauthorized();
        }
        try {
            File uploadFile = new File(filename, LocalDateTime.now(), multipartFile.getSize(), multipartFile.getBytes(), user);
            fileRepository.save(uploadFile);
        } catch (IOException e) {
            throw new ErrorInputData();
        }
    }

    @Transactional
    public void deleteFile(String authToken, String filename) {
        User user = getUserByToken(authToken);
        if (user == null) {
            throw new ErrorUnauthorized();
        }

        long deletedCount = fileRepository.deleteByUserAndFilename(user, filename);

        if (deletedCount == 0) {
            throw new ErrorDeleteFile();
        }
    }

    public byte[] downloadFile(String authToken, String filename) {
        return null;
    }

    public void editFile(String authToken, String filename, RequestEditFileName requestEditFileName) {

    }

    public List<ResponseFile> getAllFiles(String authToken, Integer limit) {
        return null;
    }

    public User getUserByToken(String authToken) {
        if (authToken.startsWith("Bearer ")) {
            String tokenWithoutBearer = authToken.substring(7);
            return authRepository.getUserByToken(tokenWithoutBearer);
        } else return null;
    }
}
