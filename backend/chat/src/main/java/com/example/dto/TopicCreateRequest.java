package com.example.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TopicCreateRequest {
  @NotEmpty(message = "topicId는 필수값입니다.")
  private final String topicId;
  @NotNull(message = "expireAt은 필수값입니다.")
  private final LocalDateTime expireAt;
}
