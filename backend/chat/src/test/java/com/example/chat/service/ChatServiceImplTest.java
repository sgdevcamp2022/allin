package com.example.chat.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.example.domain.Topic;
import com.example.dto.ChatMessage;
import com.example.service.ChatServiceImpl;
import com.example.service.MessagePublisher;
import com.example.service.TopicService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChatServiceImplTest {

  @Mock
  TopicService topicService;

  @Mock
  MessagePublisher messagePublisher;

  @InjectMocks
  ChatServiceImpl chatService;

  @Nested
  @DisplayName("send 메서드는")
  class DescribeSend {

    @Nested
    @DisplayName("유효한 값이 전달되면")
    class ContextWithValidData {

      @Test
      @DisplayName("publisher.publish()를 호출한다")
      void ItCallsPublish() {
        // given
        String id = "topic1";
        ChatMessage message = ChatMessage.of("user1", "message1");
        given(topicService.findById(anyString()))
          .willReturn(Topic.from(id, LocalDateTime.now()));

        // when
        chatService.send(id, message);

        // then
        verify(messagePublisher).publish(anyString(), any(ChatMessage.class));
      }
    }

    @Nested
    @DisplayName("존재하지 않는 토픽이라면")
    class ContextWithNonexistentTopic {

      @Test
      @DisplayName("IllegalArgumentException 에러를 발생시킨다")
      void ItThrowsIllegalArgumentException() {
        // given
        String id = "topic1";
        ChatMessage message = ChatMessage.of("user1", "message1");
        given(topicService.findById(anyString()))
          .willThrow(new IllegalArgumentException());

        // when, then
        assertThatThrownBy(() -> chatService.send(id, message))
          .isInstanceOf(IllegalArgumentException.class);
      }
    }
  }
}
