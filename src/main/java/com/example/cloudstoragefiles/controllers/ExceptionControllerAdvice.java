package com.example.cloudstoragefiles.controllers;

import com.example.cloudstoragefiles.exceptions.ErrorBadCredentials;
import com.example.cloudstoragefiles.models.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ErrorBadCredentials.class)
    public ResponseEntity<ResponseError> handlerErrorInputData() {
        ResponseError errorResponse = new ResponseError("Error Bad Credentials", 0);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
