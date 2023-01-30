package com.example.service;

import com.example.domain.Topic;
import java.time.LocalDateTime;

public interface TopicService {

  void create(String id, LocalDateTime expireAt);

  Topic findById(String id);
}
