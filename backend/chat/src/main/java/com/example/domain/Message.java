package com.example.domain;

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

  private String topicId;

  private String sender;

  private String content;

  @CreatedDate
  LocalDateTime createAt;

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
