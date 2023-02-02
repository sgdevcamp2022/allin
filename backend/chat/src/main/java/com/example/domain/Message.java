package com.example.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
@Getter
@Builder(access = AccessLevel.PRIVATE)
public class Message {

  @Id
  private String id;

  @NotBlank
  private String topicId;

  @NotBlank
  @Size(max = 255)
  private String sender;

  @NotBlank
  @Size(max = 100)
  private String content;

  @CreatedDate
  LocalDateTime createAt;

  @NotNull
  @Indexed(expireAfterSeconds = 0)
  private LocalDateTime expireAt;

  public static Message of(String topicId, String sender, String content, LocalDateTime expireAt) {
    return Message.builder()
                  .topicId(topicId)
                  .sender(sender)
                  .content(content)
                  .expireAt(expireAt)
                  .build();
  }
}
