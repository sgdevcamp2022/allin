package com.All_IN.manager.service.publisher;

import com.All_IN.manager.domain.publisher.Publisher;
import com.All_IN.manager.domain.publisher.PublisherRepository;
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
class PublisherValidateServiceExceptionTest {


    @Autowired
    PublisherService publisherService;

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    PublisherValidateService publisherValidateService;


    static Long publisherId = 1L;

    static Long memberId = 1L;

    static String key;

    static String password;

    @BeforeEach
    void data() {
        publisherService.save(memberId);

        key = publisherService.getKey(publisherId);
        password = publisherService.generatePassword(publisherId);
    }

    @AfterEach
    void clear() {
        clearData();

        publisherId++;
        memberId++;
    }

    private void clearData() {
        publisherRepository.deleteAll();
    }


    @Test
    void validatePublisher_byPublisherId() {
        //Given

        //When

        //Then
        Assertions.assertThatThrownBy(
            () -> publisherValidateService.validatePublisher(publisherId + 1L,
                PublisherValidateIdType.PUBLISHER)).hasMessage(
            PublisherServiceException.NO_SUCH_PUBLISHER.getMessage());
    }

    @Test
    void validatePublisher_byMemberId() {
        //Given

        //When

        //Then
        Assertions.assertThatThrownBy(
            () -> publisherValidateService.validatePublisher(memberId + 1L,
                PublisherValidateIdType.PUBLISHER)).hasMessage(
            PublisherServiceException.NO_SUCH_PUBLISHER.getMessage());
    }

    @Test
    void validatePublisher_byKeyAndPassword_NO_PASSORD() {
        //Given

        //When

        //Then
        Assertions.assertThatThrownBy(
            () -> publisherValidateService.validatePublisher("key", password)).hasMessage(
            PublisherServiceException.NO_SUCH_PUBLISHER.getMessage());
    }

    @Test
    void validatePublisher_byKeyAndPassword_NO_MATCH_PASSORD() {
        //Given

        //When

        //Then
        Assertions.assertThatThrownBy(
            () -> publisherValidateService.validatePublisher(key, "password")).hasMessage(
            PublisherServiceException.NO_MATCH_PASSWORD.getMessage());
    }

    @Test
    void validatePublisher_byKey_NO_SUCH_PUBLISHER() {
        //Given

        //When

        //Then
        Assertions.assertThatThrownBy(
            () -> publisherValidateService.validatePublisher("key")).hasMessage(
            PublisherServiceException.NO_SUCH_PUBLISHER.getMessage());
    }
}