package com.All_IN.manager.service.publisher;

import com.All_IN.manager.domain.publisher.Publisher;
import com.All_IN.manager.domain.publisher.PublisherPassword;
import com.All_IN.manager.domain.publisher.PublisherPasswordRepository;
import com.All_IN.manager.domain.publisher.PublisherRepository;
import com.All_IN.manager.service.publisher.exception.PublisherServiceException;
import com.All_IN.manager.service.publisher.exception.PublisherServiceValidateException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PublisherService {

    private final PublisherRepository repository;
    private final PublisherPasswordRepository publisherPasswordRepository;

    @Transactional
    public void save(long memberId) {
        Optional<Publisher> byMemberId = repository.findByMemberId(memberId);
        if (byMemberId.isPresent()) {
            throw new PublisherServiceValidateException(PublisherServiceException.EXIST_PUBLISHER);
        }

        repository.save(new Publisher(memberId));
    }

    public String getKey(Long publisherId) {
        Publisher publisher = repository.findById(publisherId)
            .orElseThrow(() -> new PublisherServiceValidateException(
                PublisherServiceException.NO_SUCH_PUBLISHER));

        return publisher.getKey();
    }

    @Transactional
    public String generatePassword(Long publisherId) {
        Publisher publisher = repository.findById(publisherId)
            .orElseThrow(() -> new PublisherServiceValidateException(
                PublisherServiceException.NO_SUCH_PUBLISHER));

        Optional<PublisherPassword> passwordByPublisher = publisherPasswordRepository.findByPublisher(
            publisher);
        if (passwordByPublisher.isPresent()) {
            throw new PublisherServiceValidateException(
                PublisherServiceException.ALREADY_GENERATE_PASSWORD);
        }

        PublisherPassword publisherPassword = new PublisherPassword(publisher);
        publisherPasswordRepository.save(publisherPassword);

        return publisherPassword.getValue();
    }

    @Transactional
    public void resetPassword(Long publisherId) {
        Publisher publisher = repository.findById(publisherId)
            .orElseThrow(() -> new PublisherServiceValidateException(
                PublisherServiceException.NO_SUCH_PUBLISHER));

        PublisherPassword publisherPassword = publisherPasswordRepository.findByPublisher(
            publisher).orElseThrow(
            () -> new PublisherServiceValidateException(PublisherServiceException.NO_PASSWORD));

        publisherPassword.use();

        // todo password 반환으로
    }

    public String generateURL(Long publisherId) {
        Publisher publisher = repository.findById(publisherId)
            .orElseThrow(() -> new PublisherServiceValidateException(
                PublisherServiceException.NO_SUCH_PUBLISHER));

        publisherPasswordRepository.findByPublisher(
            publisher).orElseThrow(
            () -> new PublisherServiceValidateException(PublisherServiceException.NO_PASSWORD));

        return publisher.getKey() + "?pw=";
    }

    public void validatePublisher(String key, String password) {
        Publisher publisher = repository.findByKey(key)
            .orElseThrow(() -> new PublisherServiceValidateException(
                PublisherServiceException.NO_SUCH_PUBLISHER));

        PublisherPassword publisherPassword = publisherPasswordRepository.findByPublisher(
            publisher).orElseThrow(
            () -> new PublisherServiceValidateException(PublisherServiceException.NO_PASSWORD));

        if (!publisherPassword.checkPassword(password)) {
            throw new PublisherServiceValidateException(
                PublisherServiceException.NO_MATCH_PASSWORD);
        }

    }

    @Transactional
    public void usePassword(String password) {
        PublisherPassword publisherPassword = publisherPasswordRepository.findByValue(
            password).orElseThrow(
            () -> new PublisherServiceValidateException(
                PublisherServiceException.NO_MATCH_PASSWORD));

        publisherPassword.use();
    }

    @Transactional
    public void updateKey(Long publisherId) {
        Publisher publisher = repository.findById(publisherId)
            .orElseThrow(() -> new PublisherServiceValidateException(
                PublisherServiceException.NO_SUCH_PUBLISHER));

        publisher.updateKey();
    }
}
