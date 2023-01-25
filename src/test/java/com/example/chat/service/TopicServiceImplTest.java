package com.example.chat.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

import com.example.chat.repository.TopicRepository;
import com.example.entity.Topic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@ExtendWith(MockitoExtension.class)
class TopicServiceImplTest {

  @Mock
  TopicRepository topicRepository;

  @Mock
  RedisMessageListenerContainer redisMessageListenerContainer;

  @Mock
  RedisSubscriber redisSubscriber;

  @InjectMocks
  TopicServiceImpl topicService;

  @Nested
  @DisplayName("create 메서드는")
  class DescribeCreate {

    @Nested
    @DisplayName("유효한 값이 전달되면")
    class ContextWithValidData {

      @Test
      @DisplayName("리스너를 등록하고 채팅방을 저장한다")
      void ItSavesTopic() {
        // given
        String id = "topic1";
        willDoNothing().given(redisMessageListenerContainer)
                       .addMessageListener(any(MessageListener.class), any(
                         ChannelTopic.class));
        given(topicRepository.save(any(Topic.class)))
          .willReturn(Topic.from(id));

        // when
        topicService.create(id);

        // then
        verify(redisMessageListenerContainer).addMessageListener(any(MessageListener.class), any(
          ChannelTopic.class));
        verify(topicRepository).save(any(Topic.class));
      }
    }
  }
}
