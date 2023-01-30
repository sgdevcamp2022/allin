package com.example.service;

import static com.example.exception.ErrorMessage.NONEXISTENT_TOPIC;

import com.example.domain.Topic;
import com.example.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

  private final TopicRepository topicRepository;
  private final RedisMessageListenerContainer redisMessageListenerContainer;
  private final RedisSubscriber redisSubscriber;

  /*
  TODO: ttl 값 설정 필요
   */
  @Override
  public void create(String id) {
    redisMessageListenerContainer.addMessageListener(redisSubscriber,
      new org.springframework.data.redis.listener.ChannelTopic(id));
    topicRepository.save(Topic.from(id));
  }

  @Override
  public Topic findById(String id) {
    return topicRepository.findById(id).orElseThrow(
      () -> new IllegalArgumentException(NONEXISTENT_TOPIC.getMessage()));
  }
}
