package com.All_IN.manager.service.braodCast.dto;

import com.All_IN.manager.domain.broadCast.BroadCast;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class OnLiveBroadCastResponsesList {

    private List<BroadCastResponseDTO> liveBroadCasts;

    private OnLiveBroadCastResponsesList(List<BroadCastResponseDTO> liveBroadCasts) {
        this.liveBroadCasts = liveBroadCasts;
    }

    public static OnLiveBroadCastResponsesList of(List<BroadCast> liveBroadCastList) {
        List<BroadCastResponseDTO> broadCastResponseDTOList = liveBroadCastList
            .stream()
            .map(BroadCastResponseDTO::new)
            .collect(Collectors.toList());

        return new OnLiveBroadCastResponsesList(broadCastResponseDTOList);
    }

}
