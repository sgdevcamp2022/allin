package com.All_IN.manager.mapper.broadCast;

import java.util.List;
import lombok.Data;

@Data
public class OnLiveBroadCastListDTO {

    private List<BroadCastDTO> liveBroadCasts;


    public OnLiveBroadCastListDTO(List<BroadCastDTO> liveBroadCasts) {
        this.liveBroadCasts = liveBroadCasts;
    }

}
