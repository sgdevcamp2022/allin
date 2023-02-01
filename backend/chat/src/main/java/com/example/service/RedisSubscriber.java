package com.example.service;

import com.example.dto.ChatMessageRequest;
import com.example.dto.ChatMessageResponse;
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
    ChatMessageRequest chatMessage = null;
    try {
      chatMessage = objectMapper.readValue(messageBody, ChatMessageRequest.class);
    } catch (JsonProcessingException e) {
      log.info(e.getMessage());
      return;
    }
    ChatMessageResponse sendChatMessage = ChatMessageResponse.of(chatMessage.getSender(),
      chatMessage.getContent());
    simpMessageSendingOperations.convertAndSend("/topic/" + id, sendChatMessage);
  }
}
