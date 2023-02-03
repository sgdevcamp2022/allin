package com.example.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.RedisTestContainerConfig;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@ExtendWith(RedisTestContainerConfig.class)
@DataRedisTest
class ReportCountRepositoryTest {

  @Autowired
  RedisTemplate redisTemplate;

  ReportCountRepository reportCountRepository;

  @PostConstruct
  void setup() {
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());
    reportCountRepository = new ReportCountRepository(redisTemplate);

  }

  final String topicId = "topic1";
  final String reportedUser = "user3";
  final String key = topicId + "::" + reportedUser;

  @AfterEach
  void clear() {
    redisTemplate.delete(key);
  }

  @Nested
  @DisplayName("increaseReportCount 메서드는")
  class DescribeIncreaseReportCount {

    @Nested
    @DisplayName("저장되어 있지 않은 key라면")
    class ContextWithUnsavedKey {

      @Test
      @DisplayName("데이터를 저장한다")
      void ItSavesData() {
        // given
        // when
        reportCountRepository.increaseReportCount(topicId, reportedUser);

        // then
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String count = (String) valueOperations.get(key);
        assertThat(count).isEqualTo("1");
      }
    }

    @Nested
    @DisplayName("이미 저장되어 있는 key라면")
    class ContextWithSavedKey {

      @Test
      @DisplayName("채팅 메시지 리스트를 반환한다")
      void ItIncreasesCount() {
        // given
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, "1");

        // when
        reportCountRepository.increaseReportCount(topicId, reportedUser);

        // then
        String count = (String) valueOperations.get(key);
        assertThat(count).isEqualTo("2");
      }
    }
  }
}
