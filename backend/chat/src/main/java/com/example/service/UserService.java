package com.example.service;

public interface UserService {

  void block(String topicId, String name);
  boolean isBlockedUser(String topicId, String name);
}
