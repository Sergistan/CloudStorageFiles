package com.example.cloudstoragefiles.controllers;

import com.example.cloudstoragefiles.exceptions.ErrorBadCredentials;
import com.example.cloudstoragefiles.exceptions.ErrorDeleteFile;
import com.example.cloudstoragefiles.exceptions.ErrorInputData;
import com.example.cloudstoragefiles.exceptions.ErrorUnauthorized;
import com.example.cloudstoragefiles.models.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ErrorBadCredentials.class)
    public ResponseEntity<ResponseError> handlerBadCredentials() {
        ResponseError errorResponse = new ResponseError("Error Bad Credentials", 0);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorUnauthorized.class)
    public ResponseEntity<ResponseError> handlerErrorUnauthorized() {
        ResponseError errorResponse = new ResponseError("Error Unauthorized", 0);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ErrorInputData.class)
    public ResponseEntity<ResponseError> handlerErrorInputData() {
        ResponseError errorResponse = new ResponseError("Error Input Data", 0);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorDeleteFile.class)
    public ResponseEntity<ResponseError> handlerDeleteFile() {
        ResponseError errorResponse = new ResponseError("Error Delete File", 0);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
