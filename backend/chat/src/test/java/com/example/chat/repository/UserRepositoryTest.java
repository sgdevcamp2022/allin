package com.example.chat.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.chat.RedisTestContainerConfig;
import com.example.domain.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;

@ExtendWith(RedisTestContainerConfig.class)
@DataRedisTest
class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;


  @AfterEach
  void clear() {
    userRepository.deleteAll();
  }

  @Nested
  @DisplayName("existsByTopicIdAndName 메서드는")
  class DescribeExistsByTopicIdAndName {

    @Nested
    @DisplayName("존재하는 유저라면")
    class ContextWithUserExist {

      @Test
      @DisplayName("true를 반환한다")
      void ItReturnsTrue() {
        // given
        User user = User.of("topic1", "user1");
        userRepository.save(user);

        // when
        boolean result = userRepository.existsByTopicIdAndName(user.getTopicId(),
          user.getName());

        // then
        assertThat(result).isNotNull();
      }
    }

    @Nested
    @DisplayName("존재하지 않는 유저라면")
    class ContextWithNonexistentUser {

      @Test
      @DisplayName("false를 반환한다")
      void ItReturnsFalse() {
        // given

        // when
        boolean result = userRepository.existsByTopicIdAndName("anyTopic1", "anyUser1");

        // then
        assertThat(result).isFalse();
      }
    }
  }
}
