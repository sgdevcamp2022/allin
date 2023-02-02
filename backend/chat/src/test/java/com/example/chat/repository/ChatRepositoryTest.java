package com.example.chat.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.chat.MongoTestContainerConfig;
import com.example.domain.Message;
import com.example.repository.ChatRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MongoTestContainerConfig.class)
@DataMongoTest
class ChatRepositoryTest {

  @Autowired
  ChatRepository chatRepository;

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
        createChatMessage();

        // when
        List<Message> result = chatRepository.findAll(PageRequest.of(0, 19));

        // then
        assertThat(result.size()).isEqualTo(19);
      }
    }
  }

  private void createChatMessage() {
    for (int i = 0; i < 20; i++) {
      Message message = Message.of("topic1", "마틴파울러", "hi~", LocalDateTime.now().plusMinutes(10));
      chatRepository.save(message);
    }
  }
}
