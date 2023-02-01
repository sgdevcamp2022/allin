package com.example.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@RedisHash
public class Topic {

  @Id
  private final String id;

  private final LocalDateTime expireAt;

  public static Topic from(String id, LocalDateTime expireAt) {
    return Topic.builder()
                .id(id)
                .expireAt(expireAt)
                .build();
  }
}
