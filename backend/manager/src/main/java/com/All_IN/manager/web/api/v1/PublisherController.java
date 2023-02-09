package com.All_IN.manager.web.api.v1;

import com.All_IN.manager.service.publisher.PublisherService;
import com.All_IN.manager.web.dto.KeyResponse;
import com.All_IN.manager.web.dto.PasswordResponse;
import com.All_IN.manager.web.dto.UrlResponse;
import com.All_IN.manager.web.response.ApiResponse;
import com.All_IN.manager.web.response.ApiResponse.withCodeAndMessage;
import com.All_IN.manager.web.response.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/publisher")
public class PublisherController {

    private static String ManagerServerCode = "400";


    private final PublisherService publisherService;


    @PostMapping
    public ApiResponse<ApiResponse.withCodeAndMessage> makeNewPublisher(Long memberId) {
        publisherService.save(memberId);

        return ApiResponseGenerator.success(
            HttpStatus.OK,
            HttpStatus.OK.value() + ManagerServerCode,
            "success convert type to Publisher"
        );
    }

    @PostMapping("/key")
    public ApiResponse<ApiResponse.withData> generateKey(Long publisherId) {
        String key = publisherService.getKey(publisherId);

        return ApiResponseGenerator.success(
            new KeyResponse(key),
            HttpStatus.OK,
            HttpStatus.OK.value() + ManagerServerCode,
            "success generate key"
        );
    }

    @PutMapping("/key")
    public ApiResponse<ApiResponse.withData> updateKey(Long publisherId) {
        publisherService.updateKey(publisherId);

        String key = publisherService.getKey(publisherId);

        return ApiResponseGenerator.success(
            new KeyResponse(key),
            HttpStatus.OK,
            HttpStatus.OK.value() + ManagerServerCode,
            "success update key"
        );
    }

    @PostMapping("/password")
    public ApiResponse<ApiResponse.withData> generatePassword(Long publisherId) {
        String password = publisherService.generatePassword(publisherId);

        return ApiResponseGenerator.success(
            new PasswordResponse(password),
            HttpStatus.OK,
            HttpStatus.OK.value() + ManagerServerCode,
            "success generate password"
        );
    }

    @DeleteMapping("/password")
    public ApiResponse<withCodeAndMessage> resetPassword(Long publisherId) {
        publisherService.resetPassword(publisherId);

        return ApiResponseGenerator.success(
            HttpStatus.OK,
            HttpStatus.OK.value() + ManagerServerCode,
            "success reset password, can't use password more time"
        );
    }

    @PostMapping("/url")
    public ApiResponse<ApiResponse.withData> generateUrl(Long publisherId) {
        String url = publisherService.generateURL(publisherId);

        return ApiResponseGenerator.success(
            new UrlResponse(url),
            HttpStatus.OK,
            HttpStatus.OK.value() + ManagerServerCode,
            "success generate url"
        );
    }

}