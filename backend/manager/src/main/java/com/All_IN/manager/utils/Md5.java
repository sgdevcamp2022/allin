package com.All_IN.manager.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

@Component
public class Md5 {

    private final MessageDigest md;

    public Md5() throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance("MD5");
    }

    public String encode(String inputText) {
        md.update(inputText.getBytes());

        byte[] byteData = md.digest();

        StringBuilder sb = new StringBuilder();

        for (byte byteDatum : byteData) {
            sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16)
                .substring(1));
        }

        return sb.toString();
    }

}
