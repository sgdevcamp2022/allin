package com.example.repository;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReportCountRepository {

  private static final int DURATION_IN_HOUR = 12;
  private final RedisTemplate redisTemplate;

  public void increaseReportCount(String topicId, String reportedUser) {
    String key = topicId + "::" + reportedUser;
    ValueOperations valueOperations = redisTemplate.opsForValue();
    if (valueOperations.get(key) == null) {
      valueOperations.set(key, "1", Duration.ofHours(DURATION_IN_HOUR));
      return;
    }
    valueOperations.increment(key);
  }
}
