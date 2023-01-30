package com.example.chat.service;

import com.example.entity.Topic;

public interface TopicService {

  void create(String id);

  Topic findById(String id);
}
