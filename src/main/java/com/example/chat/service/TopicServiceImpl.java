package com.example.chat.service;

import com.example.chat.repository.TopicRepository;
import com.example.entity.Topic;
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
}
