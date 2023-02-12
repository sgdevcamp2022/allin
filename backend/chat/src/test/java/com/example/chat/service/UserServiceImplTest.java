package com.example.chat.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.example.domain.User;
import com.example.repository.UserRepository;
import com.example.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  UserRepository userRepository;

  @InjectMocks
  UserServiceImpl userService;

  @Nested
  @DisplayName("block 메서드는")
  class DescribeBlock {

    @Nested
    @DisplayName("유효한 값이 전달되면")
    class ContextWithValidData {

      @Test
      @DisplayName("저장된 정보를 이용해 차단 사용자를 저장한다")
      void ItSavesUser() {
        // given
        String topicId = "topic1";
        String blockedUser = "user2";
        given(userRepository.save(any(User.class)))
          .willReturn(User.of(topicId, blockedUser));

        // when
        userService.block(topicId, blockedUser);

        // then
        verify(userRepository).save(any(User.class));
      }
    }
  }
}
