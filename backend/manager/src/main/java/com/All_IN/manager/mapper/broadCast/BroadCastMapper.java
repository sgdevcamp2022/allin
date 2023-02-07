package com.All_IN.manager.mapper.broadCast;

import com.All_IN.manager.domain.broadCast.BroadCast;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BroadCastMapper {

    public OnLiveBroadCastListDTO of(List<BroadCast> liveBroadCastList) {
        List<BroadCastDTO> broadCastDTOList = liveBroadCastList
            .stream()
            .map(BroadCastDTO::new)
            .collect(Collectors.toList());

        return new OnLiveBroadCastListDTO(broadCastDTOList);
    }

}
