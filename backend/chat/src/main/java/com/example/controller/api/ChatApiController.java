package com.example.controller.api;

import com.example.dto.ApiResponse;
import com.example.dto.ChatMessagePagingRequest;
import com.example.dto.ChatMessageResponse;
import com.example.service.ChatService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatApiController {
  private final ChatService chatService;

  @GetMapping
  ApiResponse<List<ChatMessageResponse>> findAll(@ModelAttribute @Valid ChatMessagePagingRequest request) {
    return new ApiResponse<>(chatService.findAll(request));
  }
}
