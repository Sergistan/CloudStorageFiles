package com.example.cloudstoragefiles.repositories;

import com.example.cloudstoragefiles.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.example.cloudstoragefiles.TestData.*;

public class AuthRepositoryTest {
    private AuthRepository authRepository;

    private final Map<String, User> mapTokensAndUser = new ConcurrentHashMap<>();

    @BeforeEach
    void setUp() {
        authRepository = new AuthRepository();
        authRepository.saveAuthenticationUser(TOKEN_1, USER_1);
        mapTokensAndUser.clear();
        mapTokensAndUser.put(TOKEN_1, USER_1);
    }

    @Test
    void putTokenAndUser() {
        User beforePut = authRepository.getAuthenticationUserByToken(TOKEN_2);
        Assertions.assertNull(beforePut);
        authRepository.saveAuthenticationUser(TOKEN_2, USER_2);
        User afterPut = authRepository.getAuthenticationUserByToken(TOKEN_2);
        Assertions.assertEquals(USER_2, afterPut);
    }

    @Test
    void removeAuthenticationUserByToken() {
        User beforeRemove = authRepository.getAuthenticationUserByToken(TOKEN_1);
        Assertions.assertNotNull(beforeRemove);
        authRepository.deleteAuthenticationUserByToken(TOKEN_1);
        User afterRemove = authRepository.getAuthenticationUserByToken(TOKEN_1);
        Assertions.assertNull(afterRemove);
    }

    @Test
    void getUserByToken() {
        Assertions.assertEquals(mapTokensAndUser.get(TOKEN_1), authRepository.getAuthenticationUserByToken(TOKEN_1));
    }
}
