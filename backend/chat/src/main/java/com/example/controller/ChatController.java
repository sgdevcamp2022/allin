package com.example.controller;

import com.example.dto.ChatMessageRequest;
import com.example.service.ChatService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

  private final ChatService chatService;


  @MessageMapping("/{id}/send")
  public void send(@DestinationVariable @NotEmpty String id, @Valid ChatMessageRequest message) {
    chatService.send(id, message);
  }

  @MessageExceptionHandler({Exception.class})
  public void handleException(Exception e) {
    log.info(e.getMessage());
  }
}
