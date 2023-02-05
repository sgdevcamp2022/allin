package com.All_IN.manager.service.braodCast;

import com.All_IN.manager.domain.broadCast.BroadCastRepository;
import com.All_IN.manager.domain.publisher.PublisherRepository;
import com.All_IN.manager.service.braodCast.exception.BroadCastServiceException;
import com.All_IN.manager.service.publisher.PublisherService;
import com.All_IN.manager.service.publisher.exception.PublisherServiceException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@Slf4j
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class BroadCastServiceExceptionTest {
    @Autowired
    BroadCastService broadCastService;

    @Autowired
    PublisherService publisherService;

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    BroadCastRepository broadCastRepository;

    static Long memberId = 1L;
    static Long publisherId = 1L;

    static String key;
    static String password;

    @BeforeEach
    void data() {
        publisherService.save(memberId);

        key = publisherService.getKey(publisherId);
        password = publisherService.generatePassword(publisherId);

        broadCastService.startLive(key, password);
    }

    @AfterEach
    void clear() {
        publisherId++;
        memberId++;

        clearData();
    }

    private void clearData() {
        publisherRepository.deleteAll();
        broadCastRepository.deleteAll();
    }

    @Test
    void startLive_already_on_live() {
        //Given

        //When
        Assertions.assertThatThrownBy(() -> broadCastService.startLive(key, password))
            .hasMessage(BroadCastServiceException.ALREADY_ON_LIVE.getMessage());

        //Then
    }

    @Test
    void startLive_no_such_publisher() {
        //Given

        //When
        Assertions.assertThatThrownBy(() -> broadCastService.startLive("not match key", password))
            .hasMessage(PublisherServiceException.NO_SUCH_PUBLISHER.getMessage());

        //Then
    }

    @Test
    void startLive_no_match_password() {
        //Given

        //When
        Assertions.assertThatThrownBy(() -> broadCastService.startLive(key, "not match password"))
            .hasMessage(PublisherServiceException.NO_MATCH_PASSWORD.getMessage());

        //Then
    }

    @Test
    void startLive_no_password() {
        //Given
        publisherService.resetPassword(publisherId);

        //When
        Assertions.assertThatThrownBy(() -> broadCastService.startLive(key, "not match password"))
            .hasMessage(PublisherServiceException.NO_PASSWORD.getMessage());

        //Then
    }

    @Test
    void endLive_no_match_live() {
        //Given
        broadCastService.endLive(key);

        //When
        Assertions.assertThatThrownBy(() -> broadCastService.endLive(key))
            .hasMessage(BroadCastServiceException.NO_MATCH_LIVE.getMessage());

        //Then
    }

    @Test
    void endLive_no_such_publisher() {
        //Given

        //When
        Assertions.assertThatThrownBy(() -> broadCastService.endLive("not match key"))
            .hasMessage(PublisherServiceException.NO_SUCH_PUBLISHER.getMessage());

        //Then
    }


}