package com.All_IN.manager.service.braodCast;


import com.All_IN.manager.domain.broadCast.BroadCastRepository;
import com.All_IN.manager.domain.publisher.Publisher;
import com.All_IN.manager.domain.publisher.PublisherRepository;
import com.All_IN.manager.service.publisher.PublisherService;
import com.All_IN.manager.service.publisher.PublisherValidateService;
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
    PublisherValidateService publisherValidateService;

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

        Publisher publisher = publisherValidateService.validatePublisher(key, password);

        broadCastService.startLive(publisher);
    }

    @AfterEach
    void clear() {
        clearData();

        publisherId++;
        memberId++;
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
        Publisher publisher = publisherValidateService.validatePublisher(key);

        //When
        broadCastService.endLive(publisher);
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
        Publisher publisher = publisherValidateService.validatePublisher(newKey, newPassword);

        broadCastService.startLive(publisher);

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