package com.All_IN.manager.service.braodCast;

import com.All_IN.manager.domain.broadCast.BroadCast;
import com.All_IN.manager.domain.broadCast.BroadCastRepository;
import com.All_IN.manager.domain.broadCast.BroadCastState;
import com.All_IN.manager.mapper.broadCast.BroadCastMapper;
import com.All_IN.manager.mapper.broadCast.OnLiveBroadCastListDTO;
import com.All_IN.manager.service.braodCast.exception.BroadCastServiceException;
import com.All_IN.manager.service.braodCast.exception.BroadCastServiceValidateException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BroadCastService {

    private final BroadCastRepository repository;

    private final BroadCastMapper mapper;


    @Transactional
    public void startLive(Long publisherId) {
        if (repository.existByPublisherAndStateIsLive(publisherId)) {
            throw new BroadCastServiceValidateException(BroadCastServiceException.ALREADY_ON_LIVE);
        }

        BroadCast broadCast = BroadCast.relatedFrom(publisherId);
        repository.save(broadCast);
    }

    @Transactional
    public void endLive(Long publisherId) {
        BroadCast liveBroadCast = repository.findByPublisherAndState(publisherId, BroadCastState.LIVE)
            .orElseThrow(() -> new BroadCastServiceValidateException(BroadCastServiceException.NO_MATCH_LIVE));

        liveBroadCast.end();
    }

    public OnLiveBroadCastListDTO liveList() {
        List<BroadCast> onLiveBroadCastList = repository.findAllByState(BroadCastState.LIVE);

        return mapper.of(onLiveBroadCastList);
    }

}
