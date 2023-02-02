package com.example.service;

import com.example.dto.ChatMessageRequest;

public interface ChatService {

  void send(String id, ChatMessageRequest message);
}
