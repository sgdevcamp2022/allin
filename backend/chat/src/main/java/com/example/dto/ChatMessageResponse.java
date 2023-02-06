package com.example.dto;

import static lombok.AccessLevel.PRIVATE;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class ChatMessageResponse implements Serializable {
  private static final long serialVersionUID = 6494678977089006639L;

  private String sender;
  private String content;

  @Builder(access = PRIVATE)
  public ChatMessageResponse(String sender, String content) {
    this.sender = sender;
    this.content = content;
  }

  public static ChatMessageResponse of(String sender, String content) {
    return ChatMessageResponse.builder()
                              .sender(sender)
                              .content(content)
                              .build();
  }
}
