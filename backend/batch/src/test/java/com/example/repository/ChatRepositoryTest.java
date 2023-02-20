package com.example.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.MongoTestContainerConfig;
import com.example.domain.Message;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@ExtendWith(MongoTestContainerConfig.class)
@DataMongoTest
class ChatRepositoryTest {

  @Autowired
  ChatRepository chatRepository;

  private static final String topicId = "topic1";
  private static final String sender = "마틴파울러";

  @Nested
  @DisplayName("findAllByTopicIdAndSender 메서드는")
  class DescribeFindAllByTopicIdAndSender {

    @Nested
    @DisplayName("유효한 값이 전달되면")
    class ContextWithValidData {

      @Test
      @DisplayName("채팅 메시지 리스트를 반환한다")
      void ItReturnsChatMessageList() {
        // given
        int size = 20;
        createChatMessage(size);

        // when
        List<Message> result = chatRepository.findAllByTopicIdAndSender(topicId,
          sender);

        // then
        assertThat(result).hasSize(size);
      }
    }
  }

  private void createChatMessage(int size) {
    for (int i = 0; i < size; i++) {
      Message message = Message.of(topicId, sender, "hi~", LocalDateTime.now().plusMinutes(10));
      chatRepository.save(message);
    }
  }
}
