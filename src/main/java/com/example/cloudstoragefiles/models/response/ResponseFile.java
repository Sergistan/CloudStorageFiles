package com.example.cloudstoragefiles.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseFile {
    private String filename;
    private int size;

}
