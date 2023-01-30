package com.example.service;

import com.example.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPublisher implements MessagePublisher {

  private final RedisTemplate<String, Object> redisTemplate;

  @Override
  public void publish(String topic, ChatMessage message) {
    redisTemplate.convertAndSend(topic, message);
  }
}
