package com.example.chat.service;

import com.example.chat.domain.Topic;

public interface TopicService {

  void create(String id);

  Topic findById(String id);
}
