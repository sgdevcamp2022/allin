package com.example.service;

import com.example.dto.ChatMessage;

public interface ChatService {

  void send(String id, ChatMessage message);
}
