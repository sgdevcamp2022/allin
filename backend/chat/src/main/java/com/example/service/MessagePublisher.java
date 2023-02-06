package com.example.service;


import com.example.dto.ChatMessageRequest;

public interface MessagePublisher {

  void publish(String topic, ChatMessageRequest message);
}
