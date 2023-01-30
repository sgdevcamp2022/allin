package com.example.chat.service;

import com.example.chat.dto.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisSubscriber implements MessageListener {

  private final RedisTemplate<String, Object> redisTemplate;
  private final ObjectMapper objectMapper;

  private final SimpMessageSendingOperations simpMessageSendingOperations;

  @Override
  public void onMessage(Message message, byte[] pattern) {
    String id = redisTemplate.getStringSerializer().deserialize(message.getChannel());
    String messageBody = redisTemplate.getStringSerializer()
                                      .deserialize(message.getBody());
    ChatMessage chatMessage = null;
    try {
      chatMessage = objectMapper.readValue(messageBody, ChatMessage.class);
    } catch (JsonProcessingException e) {
      log.info(e.getMessage());
      return;
    }
    simpMessageSendingOperations.convertAndSend("/topic/" + id, chatMessage);
  }
}
