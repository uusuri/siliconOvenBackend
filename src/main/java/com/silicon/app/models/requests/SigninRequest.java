package com.silicon.app.models.requests;

import lombok.Data;

@Data
public class SigninRequest {
    private String username;
    private String password;
}
