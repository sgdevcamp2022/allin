package com.example.chat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.example.domain.Message;
import com.example.domain.Topic;
import com.example.dto.ChatMessagePagingRequest;
import com.example.dto.ChatMessageRequest;
import com.example.dto.ChatMessageResponse;
import com.example.repository.ChatRepository;
import com.example.service.ChatServiceImpl;
import com.example.service.MessagePublisher;
import com.example.service.TopicService;
import com.example.service.UserService;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class ChatServiceImplTest {

  @Mock
  TopicService topicService;

  @Mock
  UserService userService;

  @Mock
  MessagePublisher messagePublisher;

  @Mock
  ChatRepository chatRepository;
  @InjectMocks
  ChatServiceImpl chatService;

  @Nested
  @DisplayName("send 메서드는")
  class DescribeSend {

    @Nested
    @DisplayName("유효한 값이 전달되면")
    class ContextWithValidData {

      @Test
      @DisplayName("publisher.publish()와 chatRepository.save()를 호출한다")
      void ItCallsPublish() {
        // given
        String id = "topic1";
        ChatMessageRequest message = ChatMessageRequest.of("user1", "message1");
        given(topicService.findById(anyString()))
          .willReturn(Topic.of(id, LocalDateTime.now()));
        given(userService.isBlockedUser(anyString(), anyString()))
          .willReturn(false);
        given(chatRepository.save(any(Message.class)))
          .willReturn(
            Message.of(id, message.getSender(), message.getContent(), LocalDateTime.now()));

        // when
        chatService.send(id, message);

        // then
        verify(messagePublisher).publish(anyString(), any(ChatMessageRequest.class));
        verify(chatRepository).save(any(Message.class));
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
        ChatMessageRequest message = ChatMessageRequest.of("user1", "message1");
        given(topicService.findById(anyString()))
          .willThrow(new IllegalArgumentException());

        // when, then
        assertThatThrownBy(() -> chatService.send(id, message))
          .isInstanceOf(IllegalArgumentException.class);
      }
    }

    @Nested
    @DisplayName("채팅이 차단된 사용자라면")
    class ContextWithBlockedUser {

      @Test
      @DisplayName("IllegalStateException 에러를 발생시킨다")
      void ItThrowsIllegalStateException() {
        // given
        String id = "topic1";
        ChatMessageRequest message = ChatMessageRequest.of("user1", "message1");
        given(topicService.findById(anyString()))
          .willReturn(Topic.of(id, LocalDateTime.now().plusMinutes(10)));
        given(userService.isBlockedUser(anyString(), anyString()))
          .willReturn(true);

        // when, then
        assertThatThrownBy(() -> chatService.send(id, message))
          .isInstanceOf(IllegalStateException.class);
      }
    }

    @Nested
    @DisplayName("이미 종료된 토픽이라면")
    class ContextWithClosedTopic {

      @Test
      @DisplayName("IllegalStateException 에러를 발생시킨다")
      void ItThrowsIllegalStateException() {
        // given
        String id = "topic1";
        ChatMessageRequest message = ChatMessageRequest.of("user1", "message1");
        Topic topic = Topic.of(id, LocalDateTime.now().minusDays(1));
        given(topicService.findById(anyString()))
          .willReturn(topic);

        // when, then
        assertThatThrownBy(() -> chatService.send(id, message))
          .isInstanceOf(IllegalStateException.class);
      }
    }
  }

  @Nested
  @DisplayName("findAll 메서드는")
  class DescribeFindAll {

    @Nested
    @DisplayName("유효한 값이 전달되면")
    class ContextWithValidData {

      @Test
      @DisplayName("채팅 메시지 리스트를 반환한다")
      void ItReturnsChatMessageList() {
        // given
        String topicId = "topic1";
        List<Message> messages = createChatMessage();
        given(topicService.findById(anyString()))
          .willReturn(Topic.of(topicId, LocalDateTime.now().plusMinutes(5)));
        given(chatRepository.findAllByTopicId(anyString(), any(Pageable.class)))
          .willReturn(messages);

        // when
        List<ChatMessageResponse> result = chatService.findAll(topicId,
          new ChatMessagePagingRequest(0));

        // then
        assertThat(result.size()).isEqualTo(messages.size());
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
        given(topicService.findById(anyString()))
          .willThrow(new IllegalArgumentException());

        // when, then
        assertThatThrownBy(() -> chatService.findAll(id, new ChatMessagePagingRequest(0)))
          .isInstanceOf(IllegalArgumentException.class);
      }
    }
  }

  private List<Message> createChatMessage() {
    List<Message> messages = new LinkedList<>();
    for (int i = 0; i < 20; i++) {
      Message message = Message.of("topic1", "마틴파울러", "hi~", LocalDateTime.now().plusMinutes(10));
      messages.add(message);
    }
    return messages;
  }
}
