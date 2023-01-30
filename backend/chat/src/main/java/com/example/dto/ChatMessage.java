package com.example.dto;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class ChatMessage implements Serializable {

  private static final long serialVersionUID = 6494678977089006639L;
  @NotEmpty
  @Size(max = 100, message = "메시지 글자 수는 최대 100자입니다.")
  private String message;

  @JsonCreator
  private ChatMessage(String message) {
    this.message = message;
  }

  public static ChatMessage from(String message) {
    return new ChatMessage(message);
  }
}
