package com.All_IN.manager.web.dto;

import lombok.Data;

@Data
public class UrlResponse {

    private String url;


    public UrlResponse(String url) {
        this.url = url;
    }

}
