
package com.example.service;

import com.example.domain.Message;
import com.example.domain.Topic;
import com.example.dto.ChatMessageRequest;
import com.example.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

  private final TopicService topicService;
  private final MessagePublisher publisher;

  private final ChatRepository chatRepository;

  @Override
  public void send(String id, ChatMessageRequest message) {
    Topic foundTopic = topicService.findById(id);
    Message sendMessage = Message.of(foundTopic.getId(), message.getSender(), message.getContent(),
      foundTopic.getExpireAt());
    chatRepository.save(sendMessage);
    publisher.publish(foundTopic.getId(), message);
  }
}
