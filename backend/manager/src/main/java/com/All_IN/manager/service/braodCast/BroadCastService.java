package com.All_IN.manager.service.braodCast;

import com.All_IN.manager.domain.broadCast.BroadCast;
import com.All_IN.manager.domain.broadCast.BroadCastRepository;
import com.All_IN.manager.domain.broadCast.BroadCastState;
import com.All_IN.manager.domain.publisher.Publisher;
import com.All_IN.manager.domain.publisher.PublisherPassword;
import com.All_IN.manager.domain.publisher.PublisherPasswordRepository;
import com.All_IN.manager.domain.publisher.PublisherRepository;
import com.All_IN.manager.service.braodCast.dto.OnLiveBroadCastResponsesList;
import com.All_IN.manager.service.braodCast.exception.BroadCastServiceException;
import com.All_IN.manager.service.braodCast.exception.BroadCastServiceValidateException;
import com.All_IN.manager.service.publisher.exception.PublisherServiceException;
import com.All_IN.manager.service.publisher.exception.PublisherServiceValidateException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BroadCastService {

    private final BroadCastRepository repository;

    private final PublisherRepository fkRepository;
    private final PublisherPasswordRepository publisherPasswordRepository;

    @Profile("test")
    @Transactional
    public void clear() {
        repository.deleteAll();
    }

    @Transactional
    public void startLive(String key, String password) {
        Publisher publisher = validatePublisher(key, password);

        if (repository.existByPublisherAndStateIsLive(publisher)) {
            throw new BroadCastServiceValidateException(BroadCastServiceException.ALREADY_ON_LIVE);
        }

        BroadCast broadCast = BroadCast.from(publisher);
        repository.save(broadCast);
    }

    private Publisher validatePublisher(String key, String password) {
        Publisher publisher = fkRepository.findByKey(key)
            .orElseThrow(
                () -> new PublisherServiceValidateException(PublisherServiceException.NO_SUCH_PUBLISHER));

        PublisherPassword publisherPassword = publisherPasswordRepository.findByPublisher(publisher)
            .orElseThrow(
                () -> new PublisherServiceValidateException(PublisherServiceException.NO_PASSWORD));

        if (!publisherPassword.checkPassword(password)) {
            throw new PublisherServiceValidateException
                (PublisherServiceException.NO_MATCH_PASSWORD);
        }
        return publisher;
    }

    @Transactional
    public void endLive(String key) {
        Publisher publisher = fkRepository.findByKey(key)
            .orElseThrow(
                () -> new PublisherServiceValidateException(PublisherServiceException.NO_SUCH_PUBLISHER));

        BroadCast liveBroadCast = repository.findByPublisherAndState(publisher, BroadCastState.LIVE)
            .orElseThrow(() -> new BroadCastServiceValidateException(BroadCastServiceException.NO_MATCH_LIVE));

        liveBroadCast.end();
    }

    public OnLiveBroadCastResponsesList liveList() {
        List<BroadCast> onLiveBroadCastList = repository.findAllByState(BroadCastState.LIVE);

        return OnLiveBroadCastResponsesList.of(onLiveBroadCastList);
    }
}
