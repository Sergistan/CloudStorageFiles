package com.example.cloudstoragefiles.controllers;

import com.example.cloudstoragefiles.models.request.RequestAuth;
import com.example.cloudstoragefiles.models.response.ResponseJWT;
import com.example.cloudstoragefiles.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController (AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseJWT> login (@Valid @RequestBody RequestAuth requestAuth) {
        ResponseJWT token = authService.login(requestAuth);
        return ResponseEntity.ok(token);
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout (@RequestHeader ("auth-token") String authToken) {
        authService.logout(authToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
