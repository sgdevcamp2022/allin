package com.All_IN.manager.service.publisher;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@Slf4j
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PublisherServiceTest {

    @Autowired
    private PublisherService publisherService;

    @Mock
    private PublisherService mockPublisherService;

    private static Long publisherId = 1L;

    @BeforeEach
    void save_data() {
        publisherService.save(999L);
        System.out.println("before publisherId = " + publisherId);
    }


    @AfterEach
    void clear() {
        publisherService.clear();
        publisherId++;
        System.out.println("after publisherId = " + publisherId);
    }

    @Test
    void save() {

        // Given

        // When
        mockPublisherService.save(1L);

        // Then
        Mockito.verify(mockPublisherService, Mockito.times(1)).save(1L);

    }

    @Test
    void getKey() {
        // Given

        // When
        String key = mockPublisherService.getKey(publisherId);
        log.info("key = {}", key);

        // Then
        Mockito.verify(mockPublisherService, Mockito.times(1)).getKey(publisherId);

    }

    @Test
    void generatePassword() {
        // Given

        // When
        String password = mockPublisherService.generatePassword(publisherId);
        log.info("password = {}", password);

        // Then
        Mockito.verify(mockPublisherService, Mockito.times(1)).generatePassword(publisherId);

    }

    @Test
    void resetPassword() {
        // Given
        String password_before = publisherService.generatePassword(publisherId);
        log.info("password_before = {}", password_before);

        // When
        publisherService.resetPassword(publisherId);

        String password_after = publisherService.generatePassword(publisherId);
        log.info("password_after = {}", password_after);

        // Then
        Assertions.assertThat(password_before.equals(password_after)).isFalse();
    }

    @Test
    void generateURL() {
        // Given
        publisherService.generatePassword(publisherId);
        String key = publisherService.getKey(publisherId);

        // When
        String url = publisherService.generateURL(publisherId);
        log.info("url = {}", url);

        // Then
        Assertions.assertThat(url).isEqualTo(key + "?pw=");
    }

    @Test
    void validatePublisher() {
        // Given
        String password = publisherService.generatePassword(publisherId);
        String key = publisherService.getKey(publisherId);

        // When
        publisherService.validatePublisher(key, password);

        // Then
    }

    @Test
    void usePassword() {
        // Given
        String password = publisherService.generatePassword(publisherId);

        // When
        publisherService.usePassword(password);

        // Then
    }

    @Test
    void updateKey() {
        // Given
        String key_before = publisherService.getKey(publisherId);

        // When
        publisherService.updateKey(publisherId);
        String key_after = publisherService.getKey(publisherId);

        // Then
        Assertions.assertThat(key_before.equals(key_after)).isFalse();
    }

}