package com.silicon.app.models.requests;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String email;
    private String password;
}

