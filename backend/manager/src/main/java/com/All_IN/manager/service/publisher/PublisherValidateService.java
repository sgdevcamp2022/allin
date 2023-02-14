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


    public Long validatePublisher(Long id, PublisherValidateIdType type) {
        switch (type) {
            case PUBLISHER:
                Publisher publisherById = repository.findById(id)
                    .orElseThrow(
                        () -> new PublisherServiceValidateException(
                            PublisherServiceException.NO_SUCH_PUBLISHER));
                return publisherById.getId();
            case MEMBER:
                Publisher publisherByMember = repository.findByMemberId(id)
                    .orElseThrow(
                        () -> new PublisherServiceValidateException(
                            PublisherServiceException.NO_SUCH_PUBLISHER));
                return publisherByMember.getId();
            default:
                throw new PublisherServiceValidateException(PublisherServiceException.NO_SUCH_VALIDATE_TYPE);
        }
    }

    public Long validatePublisher(String key) {
        Publisher publisher = repository.findByKey(key)
            .orElseThrow(
                () -> new PublisherServiceValidateException(
                    PublisherServiceException.NO_SUCH_PUBLISHER));
        return publisher.getId();
    }

    public Long validatePublisher(String key, String password) {
        Long publisherId = validatePublisher(key);

        validatePublisherPassword(publisherId, password);

        return publisherId;
    }

    private void validatePublisherPassword(Long publisherId, String password) {
        PublisherPassword publisherPassword = publisherPasswordRepository.findByPublisher(publisherId)
            .orElseThrow(
                () -> new PublisherServiceValidateException(PublisherServiceException.NO_PASSWORD));

        if (!publisherPassword.checkPassword(password)) {
            throw new PublisherServiceValidateException
                (PublisherServiceException.NO_MATCH_PASSWORD);
        }
    }

}
