package com.example.cloudstoragefiles.services;

import com.example.cloudstoragefiles.exceptions.ErrorUnauthorized;
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
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        Mockito.when(userRepository.findUserByLogin(USERNAME_1)).thenReturn(USER_1);
    }

    @Test
    void loadUserByUsername() {
        Assertions.assertEquals(USER_1, userService.loadUserByUsername(USERNAME_1));
    }

    @Test
    void loadUserByUsernameUnauthorizedException() {
        assertThrows(ErrorUnauthorized.class, () -> userService.loadUserByUsername(USERNAME_2));
    }
}
