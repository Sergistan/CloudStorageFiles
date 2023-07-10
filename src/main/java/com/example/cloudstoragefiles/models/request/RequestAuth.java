package com.example.cloudstoragefiles.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class RequestAuth {

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
