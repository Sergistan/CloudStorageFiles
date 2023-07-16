package com.example.cloudstoragefiles.models.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class RequestEditFileName {

    private String filename;

    @JsonCreator
    public RequestEditFileName(String filename) {
        this.filename = filename;
    }
}
