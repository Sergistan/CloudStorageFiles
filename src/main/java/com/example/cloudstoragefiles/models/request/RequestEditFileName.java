package com.example.cloudstoragefiles.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class RequestEditFileName {
    @NotEmpty
    private String filename;
}
