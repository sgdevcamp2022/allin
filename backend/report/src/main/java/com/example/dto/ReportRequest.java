package com.example.dto;

import com.example.common.ReportReason;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReportRequest {

  @NotEmpty(message = "topicId는 필수 입력값입니다.")
  private final String topicId;

  @NotEmpty
  @Size(max = 255, message = "닉네임 길이는 최대 255자입니다.")
  private final String reportedUser;

  @NotEmpty
  @Size(max = 100, message = "메시지 글자 수는 최대 100자입니다.")
  private final String message;

  @NotNull(message = "신고 사유는 필수 입력입니다.")
  private final ReportReason reason;
}
