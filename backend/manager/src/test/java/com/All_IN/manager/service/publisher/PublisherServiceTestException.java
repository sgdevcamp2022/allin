package com.All_IN.manager.service.publisher;

import com.All_IN.manager.domain.publisher.PublisherRepository;
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
class PublisherServiceTestException {

    @Autowired
    PublisherService publisherService;

    @Autowired
    PublisherRepository publisherRepository;

    static Long publisherId = 1L;

    @BeforeEach
    void save_data() {
        publisherService.save(1L);
    }


    @AfterEach
    void clear() {
        clearData();
        publisherId++;
    }

    private void clearData() {
        publisherRepository.deleteAll();
    }

    @Test
    void save_EXIST_PUBLISHER() {
        // Given

        // When
        Assertions.assertThatThrownBy(() -> {
            publisherService.save(1L);
            publisherId++;
        }).hasMessage(
            PublisherServiceException.EXIST_PUBLISHER.getMessage());

        // Then

    }

    @Test
    void getKey_NO_SUCH_PUBLISHER() {
        // Given

        // When

        // Then
        Assertions.assertThatThrownBy(() -> publisherService.getKey(2L))
            .hasMessage(PublisherServiceException.NO_SUCH_PUBLISHER.getMessage());
    }

    @Test
    void generatePassword_NO_SUCH_PUBLISHER() {
        // Given

        // When

        // Then
        Assertions.assertThatThrownBy(() -> publisherService.generatePassword(2L))
            .hasMessage(PublisherServiceException.NO_SUCH_PUBLISHER.getMessage());
    }

    @Test
    void generatePassword_ALREADY_GENERATE_PASSWORD() {
        // Given
        publisherService.generatePassword(publisherId);

        // When

        // Then
        Assertions.assertThatThrownBy(() -> publisherService.generatePassword(publisherId))
            .hasMessage(PublisherServiceException.ALREADY_GENERATE_PASSWORD.getMessage());
    }

    @Test
    void resetPassword_NO_SUCH_PUBLISHER() {
        // Given
        String password_before = publisherService.generatePassword(publisherId);
        log.info("password_before = {}", password_before);

        // When

        // Then
        Assertions.assertThatThrownBy(() -> publisherService.resetPassword(2L))
            .hasMessage(PublisherServiceException.NO_SUCH_PUBLISHER.getMessage());
    }

    @Test
    void resetPassword_NO_PASSWORD() {
        // Given
        String password_before = publisherService.generatePassword(publisherId);
        log.info("password_before = {}", password_before);

        // When
        publisherService.usePassword(password_before);

        // Then
        Assertions.assertThatThrownBy(() -> publisherService.resetPassword(publisherId))
            .hasMessage(PublisherServiceException.NO_PASSWORD.getMessage());
    }

    @Test
    void generateURL_NOT_EQUAL_URL() {
        // Given
        publisherService.generatePassword(publisherId);
        String key = "random";

        // When
        String url = publisherService.generateURL(publisherId);
        log.info("url = {}", url);

        // Then
        Assertions.assertThat(url).isNotEqualTo(key + "?pw=");
    }

    @Test
    void generateURL_NO_SUCH_PUBLISHER() {
        // Given

        // When

        // Then
        Assertions.assertThatThrownBy(() -> publisherService.generateURL(2L))
            .hasMessage(PublisherServiceException.NO_SUCH_PUBLISHER.getMessage());
    }

    @Test
    void generateURL_NO_PASSWORD() {
        // Given

        // When

        // Then
        Assertions.assertThatThrownBy(() -> publisherService.generateURL(publisherId))
            .hasMessage(PublisherServiceException.NO_PASSWORD.getMessage());
    }

    @Test
    void validatePublisher_NO_SUCH_PUBLISHER() {
        // Given

        // When

        // Then
        Assertions.assertThatThrownBy(() -> publisherService.validatePublisher("key", "password"))
            .hasMessage(PublisherServiceException.NO_SUCH_PUBLISHER.getMessage());
    }

    @Test
    void validatePublisher_NO_PASSWORD() {
        // Given
        String key = publisherService.getKey(publisherId);

        // When

        // Then
        Assertions.assertThatThrownBy(() -> publisherService.validatePublisher(key, "password"))
            .hasMessage(PublisherServiceException.NO_PASSWORD.getMessage());
    }

    @Test
    void validatePublisher_NO_MATCH_PASSWORD() {
        // Given
        String password = publisherService.generatePassword(publisherId);
        String key = publisherService.getKey(publisherId);

        // When

        // Then
        Assertions.assertThatThrownBy(() -> publisherService.validatePublisher(key, "password"))
            .hasMessage(PublisherServiceException.NO_MATCH_PASSWORD.getMessage());
    }

    @Test
    void usePassword_NO_MATCH_PASSWORD() {
        // Given
        String password = publisherService.generatePassword(publisherId);

        // When

        // Then
        Assertions.assertThatThrownBy(() -> publisherService.usePassword("password"))
            .hasMessage(PublisherServiceException.NO_MATCH_PASSWORD.getMessage());
    }

    @Test
    void updateKey_NO_SUCH_PUBLISHER() {
        // Given

        // When

        // Then
        Assertions.assertThatThrownBy(() -> publisherService.getKey(2L))
            .hasMessage(PublisherServiceException.NO_SUCH_PUBLISHER.getMessage());
    }
}