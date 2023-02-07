package com.example.service;

import com.example.dto.TopicCreateRequest;
import com.example.domain.Topic;

public interface TopicService {

  void create(TopicCreateRequest request);

  Topic findById(String id);
}
