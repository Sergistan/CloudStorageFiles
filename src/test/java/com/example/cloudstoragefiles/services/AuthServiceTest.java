package com.example.cloudstoragefiles.services;

import com.example.cloudstoragefiles.jwt.JWTUtils;
import com.example.cloudstoragefiles.repositories.AuthRepository;
import com.example.cloudstoragefiles.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.authentication.AuthenticationManager;

import static com.example.cloudstoragefiles.TestData.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AuthServiceTest {
    @InjectMocks
    private AuthService authService;

    @Mock
    private AuthRepository authRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTUtils jwtUtils;

    @Mock
    private UserService userService;

    @Test
    void login() {
        Mockito.when(userService.loadUserByUsername(USERNAME_1)).thenReturn(USER_1);
        Mockito.when(jwtUtils.generateToken(USER_1)).thenReturn(TOKEN_1);
        Mockito.when(userRepository.findUserByLogin(USERNAME_1)).thenReturn(USER_1);
        Assertions.assertEquals(RESPONSE_JWT, authService.login(REQUEST_AUTH));
        Mockito.verify(authenticationManager, Mockito.times(1)).authenticate(USERNAME_PASSWORD_AUTH_TOKEN);
        Mockito.verify(authRepository, Mockito.times(1)).saveAuthenticationUser(TOKEN_1, USER_1);
    }

    @Test
    void logout() {
        Mockito.when(authRepository.getAuthenticationUserByToken(BEARER_TOKEN_SUBSTRING_7)).thenReturn(USER_1);
        authService.logout(BEARER_TOKEN);
        Mockito.verify(authRepository, Mockito.times(1)).deleteAuthenticationUserByToken(BEARER_TOKEN_SUBSTRING_7);
    }
}
