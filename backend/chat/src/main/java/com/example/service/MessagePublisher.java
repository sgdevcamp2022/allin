package com.example.chat.service;

import com.example.chat.dto.ChatMessage;

public interface MessagePublisher {

  void publish(String topic, ChatMessage message);
}
