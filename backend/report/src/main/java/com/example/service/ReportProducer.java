package com.example.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportProducer {

  private final NewTopic topic;
  private final KafkaTemplate<String, ReportEvent> kafkaTemplate;

  public void send(String topicId, String reportedUser) {
    Message<ReportEvent> message = MessageBuilder
      .withPayload(new ReportEvent(topicId, reportedUser))
      .setHeader(KafkaHeaders.TOPIC, topic.name())
      .build();
    kafkaTemplate.send(message);
  }
}
