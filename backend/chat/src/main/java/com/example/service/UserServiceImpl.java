package com.example.service;

import com.example.domain.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public void block(String topicId, String name) {
    userRepository.save(User.of(topicId, name));
  }

  @Override
  public boolean isBlockedUser(String topicId, String name) {
    return userRepository.existsByTopicIdAndName(topicId, name);
  }
}
