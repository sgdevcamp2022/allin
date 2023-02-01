package com.example.dto;

import static lombok.AccessLevel.PRIVATE;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class ChatMessage implements Serializable {

  private static final long serialVersionUID = 6494678977089006639L;

  @NotEmpty
  @Size(max = 255, message = "닉네임 길이는 최대 255자입니다.")
  private String sender;

  @NotEmpty
  @Size(max = 100, message = "메시지 글자 수는 최대 100자입니다.")
  private String content;

  @Builder(access = PRIVATE)
  private ChatMessage(String sender, String content) {
    this.sender = sender;
    this.content = content;
  }

  public static ChatMessage of(String sender, String content) {
    return ChatMessage.builder()
                      .sender(sender)
                      .content(content)
                      .build();
  }
}
