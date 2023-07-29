package com.example.cloudstoragefiles.services;

import com.example.cloudstoragefiles.exceptions.*;
import com.example.cloudstoragefiles.repositories.AuthRepository;
import com.example.cloudstoragefiles.repositories.FileRepository;
import com.example.cloudstoragefiles.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static com.example.cloudstoragefiles.TestData.*;
import static org.junit.Assert.assertThrows;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FileServiceTest {
    @InjectMocks
    private FileService fileService;

    @Mock
    private FileRepository fileRepository;

    @Mock
    private AuthRepository authRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        Mockito.when(authRepository.getAuthenticationUserByToken(BEARER_TOKEN_SPLIT)).thenReturn(USER_1);
        Mockito.when(userRepository.findUserByLogin(USERNAME_1)).thenReturn(USER_1);
    }

    @Test
    void uploadFile() {
        Assertions.assertTrue(fileService.uploadFile(BEARER_TOKEN, FILENAME_1, MULTIPART_FILE));
    }

    @Test
    void uploadFileUnauthorized() {
        assertThrows(ErrorUnauthorized.class, () -> fileService.uploadFile(TOKEN_1, FILENAME_1, MULTIPART_FILE));
    }

    @Test
    void deleteFile() {
        Mockito.when(fileRepository.deleteByUserAndFilename(USER_1, FILENAME_1)).thenReturn(1);
        fileService.deleteFile(BEARER_TOKEN, FILENAME_1);
        Mockito.verify(fileRepository, Mockito.times(1)).deleteByUserAndFilename(USER_1, FILENAME_1);
    }

    @Test
    void deleteFileUnauthorized() {
        assertThrows(ErrorUnauthorized.class, () -> fileService.deleteFile(TOKEN_1, FILENAME_1));
    }

    @Test
    void deleteFileInputDataException() {
        assertThrows(ErrorInputData.class, () -> fileService.deleteFile(BEARER_TOKEN, FILENAME_EMPTY));
    }

    @Test
    void deleteFileErrorDeleteException() {
        Mockito.when(fileRepository.deleteByUserAndFilename(USER_1, FILENAME_1)).thenReturn(0);
        assertThrows(ErrorDeleteFile.class, () -> fileService.deleteFile(BEARER_TOKEN, FILENAME_1));
    }

    @Test
    void downloadFile() {
        Mockito.when(fileRepository.findByUserAndFilename(USER_1, FILENAME_1)).thenReturn(FILE_1);
        Assertions.assertEquals(FILE_CONTENT_1, fileService.downloadFile(BEARER_TOKEN, FILENAME_1));
    }

    @Test
    void downloadFileUnauthorized() {
        Mockito.when(fileRepository.findByUserAndFilename(USER_1, FILENAME_1)).thenReturn(FILE_1);
        assertThrows(ErrorUnauthorized.class, () -> fileService.downloadFile(TOKEN_1, FILENAME_1));
    }

    @Test
    void downloadFileInputDataException() {
        Mockito.when(fileRepository.findByUserAndFilename(USER_1, FILENAME_1)).thenReturn(FILE_1);
        assertThrows(ErrorInputData.class, () -> fileService.downloadFile(BEARER_TOKEN, FILENAME_2));
    }

    @Test
    void downloadFileErrorUploadException() {
        Mockito.when(fileRepository.findByUserAndFilename(USER_1, FILENAME_3)).thenReturn(FILE_WITH_NULL_FILE_CONTENT);
        assertThrows(ErrorUploadFile.class, () -> fileService.downloadFile(BEARER_TOKEN, FILENAME_3));
    }

    @Test
    void editFileName() {
        Mockito.when(fileRepository.findByUserAndFilename(USER_1, FILENAME_1)).thenReturn(FILE_1);
        fileService.editFile(BEARER_TOKEN, FILENAME_1, REQUEST_EDIT_FILE_NAME);
        Mockito.verify(fileRepository, Mockito.times(1)).setNewFilenameByUserAndFilename(NEW_FILENAME, USER_1, FILENAME_1);
    }

    @Test
    void editFileNameUnauthorized() {
        assertThrows(ErrorUnauthorized.class, () -> fileService.editFile(TOKEN_1, FILENAME_1, REQUEST_EDIT_FILE_NAME));
    }

    @Test
    void editFileNameInputDataException() {
        Mockito.when(fileRepository.findByUserAndFilename(USER_1, FILENAME_1)).thenReturn(FILE_NULL);
        assertThrows(ErrorInputData.class, () -> fileService.editFile(BEARER_TOKEN, FILENAME_1, REQUEST_EDIT_FILE_NAME));
    }

    @Test
    void editFileNameErrorUploadFileException() {
        Mockito.when(fileRepository.findByUserAndFilename(USER_1, FILENAME_1)).thenReturn(FILE_1);
        assertThrows(ErrorUploadFile.class, () -> fileService.editFile(BEARER_TOKEN, FILENAME_1, REQUEST_EDIT_FILE_NAME_REPEAT));
    }

    @Test
    void getAllFiles() {
        Mockito.when(fileRepository.findAllByUser(USER_1)).thenReturn(FILE_LIST);
        Assertions.assertEquals(RESPONSE_FILE_LIST, fileService.getAllFiles(BEARER_TOKEN, LIMIT));
    }

    @Test
    void getAllFilesUnauthorized() {
        Mockito.when(fileRepository.findAllByUser(USER_1)).thenReturn(FILE_LIST);
        assertThrows(ErrorUnauthorized.class, () -> fileService.getAllFiles(TOKEN_1, LIMIT));
    }

    @Test
    void getAllFilesInputDataException() {
        assertThrows(ErrorInputData.class, () -> fileService.getAllFiles(BEARER_TOKEN, LIMIT_NULL));
    }

    @Test
    void getAllFilesErrorGettingFileList() {
        Mockito.when(fileRepository.findAllByUser(USER_1)).thenReturn(FILE_LIST_NULL);
        assertThrows(ErrorGettingFileList.class, () -> fileService.getAllFiles(BEARER_TOKEN, LIMIT));
    }
}
