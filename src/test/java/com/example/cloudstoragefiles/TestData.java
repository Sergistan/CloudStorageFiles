package com.example.cloudstoragefiles;

import com.example.cloudstoragefiles.models.File;
import com.example.cloudstoragefiles.models.User;
import com.example.cloudstoragefiles.models.request.RequestAuth;
import com.example.cloudstoragefiles.models.request.RequestEditFileName;
import com.example.cloudstoragefiles.models.response.ResponseFile;
import com.example.cloudstoragefiles.models.response.ResponseJWT;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.util.List;

public class TestData {
    public static final String TOKEN_1 = "Token_1";
    public static final String TOKEN_2 = "Token_2";
    public static final String BEARER_TOKEN = "Bearer Token";
    public static final String BEARER_TOKEN_SPLIT = BEARER_TOKEN.split(" ")[1];
    public static final String BEARER_TOKEN_SUBSTRING_7 = BEARER_TOKEN.substring(7);
    public static final Long USER_ID_1 = 1L;
    public static final String USERNAME_1 = "Username_1";
    public static final String PASSWORD_1 = "Password_1";
    public static final User USER_1 = new User(USER_ID_1, USERNAME_1, PASSWORD_1, null);

    public static final Long USER_ID_2 = 2L;
    public static final String USERNAME_2 = "Username_2";
    public static final String PASSWORD_2 = "Password_2";
    public static final User USER_2 = new User(USER_ID_2, USERNAME_2, PASSWORD_2, null);

    public static final Long FILE_ID_1 = 1L;
    public static final String FILENAME_1 = "Filename_1";
    public static final Long SIZE_1 = 100L;
    public static final byte[] FILE_CONTENT_1 = FILENAME_1.getBytes();
    public static final File FILE_1 = new File(FILE_ID_1, FILENAME_1, LocalDateTime.now(), SIZE_1, FILE_CONTENT_1, USER_1);

    public static final Long FILE_ID_2 = 2L;
    public static final String FILENAME_2 = "Filename_2";
    public static final Long SIZE_2 = 200L;
    public static final byte[] FILE_CONTENT_2 = FILENAME_2.getBytes();
    public static final File FILE_2 = new File(FILE_ID_2, FILENAME_2, LocalDateTime.now(), SIZE_2, FILE_CONTENT_2, USER_2);

    public static final Long FILE_ID_TO_RENAME = 3L;
    public static final String TO_RENAME_FILENAME = "Rename_Filename";
    public static final Long TO_RENAME_SIZE = 300L;
    public static final byte[] TO_RENAME_FILE_CONTENT = TO_RENAME_FILENAME.getBytes();
    public static final File TO_RENAME_FILE = new File(FILE_ID_TO_RENAME, TO_RENAME_FILENAME, LocalDateTime.now(), TO_RENAME_SIZE, TO_RENAME_FILE_CONTENT, USER_1);

    public static final String NEW_FILENAME = "New_Filename";
    public static final RequestEditFileName REQUEST_EDIT_FILE_NAME = new RequestEditFileName(NEW_FILENAME);

    public static final MultipartFile MULTIPART_FILE = new MockMultipartFile(FILENAME_2, FILE_CONTENT_2);

    public static final List<File> FILE_LIST = List.of(FILE_1, FILE_2);

    public static final ResponseFile RESPONSE_FILE_1 = new ResponseFile(FILENAME_1, SIZE_1);
    public static final ResponseFile RESPONSE_FILE_2 = new ResponseFile(FILENAME_2, SIZE_2);
    public static final List<ResponseFile> RESPONSE_FILE_LIST = List.of(RESPONSE_FILE_1, RESPONSE_FILE_2);
    public static final Integer LIMIT = 100;

    public static final RequestAuth REQUEST_AUTH = new RequestAuth(USERNAME_1, PASSWORD_1);
    public static final ResponseJWT RESPONSE_JWT = new ResponseJWT(TOKEN_1);

    public static final UsernamePasswordAuthenticationToken USERNAME_PASSWORD_AUTH_TOKEN = new UsernamePasswordAuthenticationToken(USERNAME_1, PASSWORD_1);
}
