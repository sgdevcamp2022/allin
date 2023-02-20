package me.ver.Authserver7.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.ver.Authserver7.dto.UserRequestDto;
import me.ver.Authserver7.dto.UserResponseDto;
import me.ver.Authserver7.dto.TokenResponseDto;
import me.ver.Authserver7.dto.TokenRequestDto;
import me.ver.Authserver7.service.AuthService;
import me.ver.Authserver7.web.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    // 회원가입
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserResponseDto> signup(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ApiResponse.createSuccess(authService.signup(userRequestDto));
    }

    // 로그인
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TokenResponseDto> login(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ApiResponse.createSuccess(authService.login(userRequestDto));
    }

    // 토큰 재발급
    @PostMapping("/reissue")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TokenResponseDto> reissue(@Valid @RequestBody TokenRequestDto tokenRequestDto) {
        return ApiResponse.createSuccess(authService.reissue(tokenRequestDto));
    }
}
