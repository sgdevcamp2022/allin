package com.example.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChatMessagePagingRequest {

  @PositiveOrZero(message = "page는 양수이거나 0이어야합니다.")
  private final int page;
}
