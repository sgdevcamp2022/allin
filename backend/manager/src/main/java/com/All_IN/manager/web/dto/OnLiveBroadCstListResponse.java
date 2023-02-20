package com.All_IN.manager.web.dto;

import com.All_IN.manager.mapper.broadCast.BroadCastDTO;
import com.All_IN.manager.mapper.broadCast.OnLiveBroadCastListDTO;
import java.util.List;
import lombok.Data;

@Data
public class OnLiveBroadCstListResponse {
    private List<BroadCastDTO> liveBroadCasts;


    public OnLiveBroadCstListResponse(OnLiveBroadCastListDTO onLiveBroadCastListDTO) {
        this.liveBroadCasts = onLiveBroadCastListDTO.getLiveBroadCasts();
    }

}
