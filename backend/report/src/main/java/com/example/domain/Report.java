package com.example.domain;

import com.example.common.ReportReason;
import com.example.common.entity.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Report extends BaseTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @NotBlank
  @Size(max = 32)
  private String topicId;
  @NotNull
  @NotBlank
  @Size(max = 8)
  private String reportedUser;

  @NotNull
  @NotBlank
  @Size(max = 8)
  private String reporter;
  @NotNull
  @NotBlank
  @Size(max = 100)
  private String message;

  @NotNull
  @Enumerated(EnumType.STRING)
  private ReportReason reason;

  private long count;

  @Builder(access = AccessLevel.PRIVATE)
  private Report(Long id, String topicId, String reportedUser, String reporter, String content,
    ReportReason reason, long count) {
    this.id = id;
    this.topicId = topicId;
    this.reportedUser = reportedUser;
    this.reporter = reporter;
    this.message = content;
    this.reason = reason;
    this.count = count;
  }

  public static Report of(String topicId, String reportedUser, String reporter, String content,
    ReportReason reason) {
    return Report.builder()
                 .topicId(topicId)
                 .reportedUser(reportedUser)
                 .reporter(reporter)
                 .content(content)
                 .reason(reason)
                 .count(0)
                 .build();
  }

  public void increaseCount() {
    this.count++;
  }
}
