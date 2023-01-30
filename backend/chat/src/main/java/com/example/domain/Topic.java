package com.example.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@RedisHash
public class Topic {

  @Id
  private final String id;

  public static Topic from(String id) {
    return new Topic(id);
  }
}
