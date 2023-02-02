package com.All_IN.manager.service.braodCast;


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

    private static Long memberId = 1L;
    private static Long publisherId = 1L;

    private static String key;
    private static String password;

    @BeforeEach
    public void data() {
        publisherService.save(memberId);

        key = publisherService.getKey(publisherId);
        password = publisherService.generatePassword(publisherId);

        broadCastService.startLive(key, password);
    }

    @AfterEach
    public void clear() {
        publisherId++;
        memberId++;

        publisherService.clear();
        broadCastService.clear();
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