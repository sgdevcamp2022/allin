package com.example.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.RedisTestContainerConfig;
import com.example.domain.User;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@ExtendWith(RedisTestContainerConfig.class)
@DataRedisTest
class UserRepositoryTest {
  @TestConfiguration
  static class TestRedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;
    @Value("${spring.data.redis.port}")
    private int port;

    @Bean
    @Primary
    public RedisConnectionFactory testRedisConnectionFactory() {
      return new LettuceConnectionFactory(host, port);
    }

    @Bean
    @Primary
    public RedisMessageListenerContainer testRedisMessageListenerContainer(
      RedisConnectionFactory connectionFactory) {
      RedisMessageListenerContainer container = new RedisMessageListenerContainer();
      container.setConnectionFactory(connectionFactory);
      return container;
    }

    @Bean
    @Primary
    public RedisTemplate<String, Object> testRedisTemplate(
      RedisConnectionFactory connectionFactory) {
      RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
      redisTemplate.setConnectionFactory(connectionFactory);
      redisTemplate.setKeySerializer(new StringRedisSerializer());
      redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
      return redisTemplate;
    }
  }
  @Autowired
  UserRepository userRepository;

  private static final String topicId = "topic1";
  private static final String sender = "gkdud53";

  @Nested
  @DisplayName("findAllByDoneIsFalse")
  class DescribeFindAllByDoneIsFalse {

    @Nested
    @DisplayName("유효한 값이 전달되면")
    class ContextWithValidData {

      @Test
      @DisplayName("유저 리스트를 반환한다")
      void ItReturnsUserList() {
        // given
        int size = 20;
        createUser(size);

        // when
        List<User> result = userRepository.findAllByIsDone(false);

        // then
        assertThat(result.size()).isEqualTo(size);
      }
    }
  }

  private void createUser(int size) {
    for (int i = 0; i < size; i++) {
      User user = User.of(topicId, sender);
      userRepository.save(user);
    }
  }
}
