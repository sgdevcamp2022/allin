package com.All_IN.manager.web.dto;

import lombok.Data;

@Data
public class PasswordResponse {

    private String password;


    public PasswordResponse(String password) {
        this.password = password;
    }

}
