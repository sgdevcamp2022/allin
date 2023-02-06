package com.All_IN.manager.service.braodCast.dto;

import com.All_IN.manager.domain.broadCast.BroadCast;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BroadCastResponseDTO {

    private Long broadCastId;
    private LocalDateTime startTime;


    public BroadCastResponseDTO(BroadCast broadCast) {
        this.broadCastId = broadCast.getId();
        this.startTime = broadCast.getSqlDateTime().getCreateAt();
    }

}
