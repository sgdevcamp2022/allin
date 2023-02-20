package com.example.chat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

import com.example.dto.TopicCreateRequest;
import com.example.domain.Topic;
import com.example.repository.TopicRepository;
import com.example.service.RedisSubscriber;
import com.example.service.TopicServiceImpl;
import java.time.LocalDateTime;
import java.util.Optional;
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
        TopicCreateRequest request = new TopicCreateRequest(id, LocalDateTime.now());
        willDoNothing().given(redisMessageListenerContainer)
                       .addMessageListener(any(MessageListener.class), any(
                         ChannelTopic.class));
        given(topicRepository.save(any(Topic.class)))
          .willReturn(Topic.of(id, LocalDateTime.now()));

        // when
        topicService.create(request);

        // then
        verify(redisMessageListenerContainer).addMessageListener(any(MessageListener.class), any(
          ChannelTopic.class));
        verify(topicRepository).save(any(Topic.class));
      }
    }
  }

  @Nested
  @DisplayName("findById 메서드는")
  class DescribeFindById {

    @Nested
    @DisplayName("유효한 값이 전달되면")
    class ContextWithValidData {

      @Test
      @DisplayName("토픽을 찾아서 반환한다")
      void ItReturnsTopic() {
        // given
        String id = "topic1";
        given(topicRepository.findById(anyString()))
          .willReturn(Optional.of(Topic.of(id, LocalDateTime.now())));
        // when
        Topic topic = topicService.findById(id);

        // then
        assertThat(topic.getId()).isEqualTo(id);
      }
    }

    @Nested
    @DisplayName("존재하지 않는 아이디라면")
    class ContextWithNonexistentId {

      @Test
      @DisplayName("IllegalArgumentException을 던진다")
      void ItThrowsIllegalArgumentException() {
        // given
        String id = "topic1";
        given(topicRepository.findById(anyString()))
          .willReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> topicService.findById(id)).isInstanceOf(
          IllegalArgumentException.class);
      }
    }
  }
}
