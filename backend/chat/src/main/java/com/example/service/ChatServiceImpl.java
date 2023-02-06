
package com.example.service;

import com.example.domain.Message;
import com.example.domain.Topic;
import com.example.dto.ChatMessagePagingRequest;
import com.example.dto.ChatMessageRequest;
import com.example.dto.ChatMessageResponse;
import com.example.repository.ChatRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

  private static final int PAGE_SIZE = 19;
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

  @Override
  public List<ChatMessageResponse> findAll(String topicId, ChatMessagePagingRequest request) {
    Topic topic = topicService.findById(topicId);
    Pageable page = PageRequest.of(request.getPage(), PAGE_SIZE, Sort.by("createAt").descending());
    return chatRepository.findAllByTopicId(topic.getId(), page)
                         .stream()
                         .map(
                           (message) -> ChatMessageResponse.of(message.getSender(),
                             message.getContent()))
                         .collect(Collectors.toList());
  }
}
