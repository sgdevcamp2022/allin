package me.ver.Authserver7.controller;

import lombok.RequiredArgsConstructor;
import me.ver.Authserver7.dto.UserResponseDto;
import me.ver.Authserver7.dto.UserUpdateDto;
import me.ver.Authserver7.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/user")
@PreAuthorize("isAuthenticated()")
public class UserController {

    private final UserService userService;

    // 정보 조회
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyInfo() {
        return ResponseEntity.ok(userService.getMyInfo());
    }

    // 정보 수정
    @PutMapping("/update")
    public ResponseEntity<UserResponseDto> updateMyInfo(@RequestBody UserUpdateDto dto) {
        userService.updateMyInfo(dto);
        return ResponseEntity.ok(userService.getMyInfo());
    }

    // 회원 탈퇴
    @DeleteMapping("/me")
    public ResponseEntity<String> deleteMember(HttpServletRequest request) {
        userService.logout(request);
        userService.deleteMember();
        return new ResponseEntity<>("회원 탈퇴 성공", HttpStatus.OK);
    }

    // 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        userService.logout(request);
        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }
}