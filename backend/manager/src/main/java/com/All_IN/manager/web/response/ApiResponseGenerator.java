package com.All_IN.manager.web.response;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

@UtilityClass
public class ApiResponseGenerator {

    public static <D> ApiResponse<ApiResponse.withData> success(final D data,
                                                                final HttpStatus status,
                                                                final String code, final String message) {
        return new ApiResponse<>(new ApiResponse.withData<>(data, code, message), status);
    }

    public static ApiResponse<ApiResponse.withCodeAndMessage> success(
            final HttpStatus status,
            final String code, final String message) {
        return new ApiResponse<>(new ApiResponse.withCodeAndMessage(code, message), status);
    }

    public static ApiResponse<ApiResponse.FailureBody> fail(
            final HttpStatus status,
            final String code, final String error, final String message) {
        return new ApiResponse<>(new ApiResponse.FailureBody(code, error, message), status);
    }
}
