package com.example.cloudstoragefiles.services;

import com.example.cloudstoragefiles.exceptions.ErrorBadCredentials;
import com.example.cloudstoragefiles.jwt.JWTUtils;
import com.example.cloudstoragefiles.models.User;
import com.example.cloudstoragefiles.models.request.RequestAuth;
import com.example.cloudstoragefiles.models.response.ResponseJWT;
import com.example.cloudstoragefiles.repositories.AuthRepository;
import com.example.cloudstoragefiles.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository, AuthRepository authRepository, JWTUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authRepository = authRepository;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    public ResponseJWT login(RequestAuth requestAuth) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestAuth.getEmail(), requestAuth.getPassword()));
        } catch (ErrorBadCredentials e)
        {
            throw new ErrorBadCredentials();
        }
        User user = userRepository.findUserByEmail(requestAuth.getEmail());
        String token = jwtUtils.generateToken(user);
        authRepository.saveAuthenticationUser(token, user);
        return new ResponseJWT(token);
    }


    public void logout(String authToken) {
        String jwt = authToken.substring(7);
        authRepository.deleteAuthenticationUserByToken(jwt);
    }
}