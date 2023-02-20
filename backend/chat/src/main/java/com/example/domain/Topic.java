package com.example.domain;

import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@RedisHash
public class Topic {

  @Id
  private final String id;

  @NotNull
  @TimeToLive
  private final long expiration;

  private final LocalDateTime expireAt;

  public static Topic of(String id, LocalDateTime expireAt) {
    return Topic.builder()
                .id(id)
                .expireAt(expireAt)
                .expiration(calculateDuration(expireAt))
                .build();
  }

  public boolean isClose() {
    return LocalDateTime.now().isAfter(expireAt);
  }

  private static long calculateDuration(LocalDateTime expireAt) {
    return Duration.between(LocalDateTime.now(), expireAt).getSeconds();
  }
}
