package com.All_IN.manager.service.publisher;

import com.All_IN.manager.domain.publisher.Publisher;
import com.All_IN.manager.domain.publisher.PublisherPassword;
import com.All_IN.manager.domain.publisher.PublisherPasswordRepository;
import com.All_IN.manager.domain.publisher.PublisherRepository;
import com.All_IN.manager.service.publisher.exception.PublisherServiceException;
import com.All_IN.manager.service.publisher.exception.PublisherServiceValidateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PublisherValidateService {

    private final PublisherRepository repository;

    private final PublisherPasswordRepository publisherPasswordRepository;


    public Publisher validatePublisher(Long id, PublisherValidateIdType type) {
        switch (type) {
            case PUBLISHER:
                return repository.findById(id)
                    .orElseThrow(
                        () -> new PublisherServiceValidateException(
                            PublisherServiceException.NO_SUCH_PUBLISHER));
            case MEMBER:
                return repository.findByMemberId(id)
                    .orElseThrow(
                        () -> new PublisherServiceValidateException(PublisherServiceException.NO_SUCH_PUBLISHER));
            default:
                throw new PublisherServiceValidateException(PublisherServiceException.NO_SUCH_VALIDATE_TYPE);
        }
    }

    public Publisher validatePublisher(String key) {
        return repository.findByKey(key)
            .orElseThrow(
                () -> new PublisherServiceValidateException(
                    PublisherServiceException.NO_SUCH_PUBLISHER));
    }

    public Publisher validatePublisher(String key, String password) {
        Publisher publisher = validatePublisher(key);

        validatePublisherPassword(publisher, password);

        return publisher;
    }

    private void validatePublisherPassword(Publisher publisher, String password) {
        PublisherPassword publisherPassword = publisherPasswordRepository.findByPublisher(publisher)
            .orElseThrow(
                () -> new PublisherServiceValidateException(PublisherServiceException.NO_PASSWORD));

        if (!publisherPassword.checkPassword(password)) {
            throw new PublisherServiceValidateException
                (PublisherServiceException.NO_MATCH_PASSWORD);
        }
    }

}
