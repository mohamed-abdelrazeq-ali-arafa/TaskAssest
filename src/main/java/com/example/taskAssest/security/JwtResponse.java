package com.example.taskAssest.security;

import lombok.Data;

@Data
public class JwtResponse {

    private String token;
    private String status;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
