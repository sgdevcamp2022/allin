package com.example.service;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ReportEvent {

  @NotEmpty
  private String topicId;
  @NotEmpty
  private String reportedUser;

  public ReportEvent(String topicId, String reportedUser) {
    this.topicId = topicId;
    this.reportedUser = reportedUser;
  }
}
