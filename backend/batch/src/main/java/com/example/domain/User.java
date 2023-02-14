package com.example.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@RedisHash
public class User {

  @Id
  private final String id;

  @NotNull
  @Indexed
  private final String topicId;

  @NotNull
  @Indexed
  private final String name;

  @Indexed
  private boolean isDone;


  public static User of(String topicId, String name) {
    return User.builder()
               .topicId(topicId)
               .name(name)
               .isDone(false)
               .build();
  }

  public void done() {
    isDone = true;
  }
}
