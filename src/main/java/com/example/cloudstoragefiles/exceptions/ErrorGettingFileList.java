package com.example.cloudstoragefiles.exceptions;

public class ErrorGettingFileList extends RuntimeException{
    public ErrorGettingFileList() {
        super("Error getting file list");
    }
}
