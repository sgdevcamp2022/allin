package com.example.service;

import com.example.domain.Topic;

public interface TopicService {

  void create(String id);

  Topic findById(String id);
}
