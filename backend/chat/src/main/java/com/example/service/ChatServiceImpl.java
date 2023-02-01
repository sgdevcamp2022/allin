
package com.example.service;

import com.example.domain.Topic;
import com.example.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

  private final TopicService topicService;
  private final MessagePublisher publisher;

  @Override
  public void send(String id, ChatMessage message) {
    Topic foundTopic = topicService.findById(id);
    publisher.publish(foundTopic.getId(), message);
  }
}
