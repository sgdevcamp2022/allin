package com.example.service;


import com.example.dto.ChatMessage;

public interface MessagePublisher {

  void publish(String topic, ChatMessage message);
}
