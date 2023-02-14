package com.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportConsumer {

  private final UserService userService;

  @KafkaListener(
    topics = "${spring.kafka.topicName}",
    groupId = "${spring.kafka.consumer.group-id}"
  )

  public void consume(ReportEvent reportEvent) {
    userService.block(reportEvent.getTopicId(), reportEvent.getReportedUser());
  }
}
