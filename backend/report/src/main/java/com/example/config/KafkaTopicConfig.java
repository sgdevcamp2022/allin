package com.example.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@ConfigurationProperties("spring.kafka")
@RequiredArgsConstructor
public class KafkaTopicConfig {
  private final String topicName;

  @Bean
  public NewTopic topic() {
    return TopicBuilder.name(topicName)
      .build();
  }
}
