package com.All_IN.manager.web.dto;

import lombok.Data;

@Data
public class KeyResponse {

    private String key;


    public KeyResponse(String key) {
        this.key = key;
    }

}
