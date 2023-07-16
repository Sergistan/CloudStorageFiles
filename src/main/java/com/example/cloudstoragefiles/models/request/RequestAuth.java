package com.example.cloudstoragefiles.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class RequestAuth {

    @NotBlank
    private String login;
    @NotBlank
    private String password;
}
