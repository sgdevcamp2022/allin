package com.All_IN.manager.service.braodCast;


import com.All_IN.manager.domain.broadCast.BroadCastRepository;
import com.All_IN.manager.domain.publisher.PublisherRepository;
import com.All_IN.manager.service.publisher.PublisherService;
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
class BroadCastServiceTest {

    @Autowired
    BroadCastService broadCastService;

    @Autowired
    PublisherService publisherService;


    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    BroadCastRepository broadCastRepository;

    Long memberId = 1L;
    Long publisherId = 1L;

    String key;
    String password;

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
    void startLive() {
        //Given

        //When
        int onLive = broadCastService.liveList().getLiveBroadCasts().size();

        //Then
        Assertions.assertThat(onLive).isEqualTo(1L);
    }

    @Test
    void endLive() {
        //Given

        //When
        broadCastService.endLive(key);
        int onLive = broadCastService.liveList().getLiveBroadCasts().size();

        //Then
        Assertions.assertThat(onLive).isEqualTo(0L);
    }

    @Test
    void liveList() {
        //Given
        Long newPublisherId = newPublisher();
        String newKey = publisherService.getKey(newPublisherId);
        String newPassword = publisherService.generatePassword(newPublisherId);

        broadCastService.startLive(newKey, newPassword);

        //When
        int onLive = broadCastService.liveList().getLiveBroadCasts().size();

        //Then
        Assertions.assertThat(onLive).isEqualTo(2L);
    }


    private Long newPublisher() {
        memberId++;
        publisherService.save(memberId);
        return ++publisherId;
    }

}