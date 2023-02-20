package com.example.common;

import static lombok.AccessLevel.PRIVATE;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = PRIVATE)
@Getter
public enum ReportReason {
  SPAM_OR_DUPLICATE_WRITING("스팸홍보/도배글입니다."),
  PORNOGRAPHY("음란물입니다."),
  ILLEGAL_INFORMATION("불법정보를 포함하고 있습니다."),
  HARM_TO_ADOLESCENTS("청소년에게 유해한 내용입니다."),
  SWEAR_WORD_OR_LIFE_THREAT_OR_HATE_EXPRESSION_OR_DISCRIMINATORY_EXPRESSION(
    "욕설/생명경시/혐오/차별적 표현입니다."),
  PERSONAL_INFORMATION_DISCLOSURE("개인정 노출 게시물입니다."),
  UNPLEASANT_EXPRESSION("불쾌한 표현이 있습니다.");

  private final String reason;
}
