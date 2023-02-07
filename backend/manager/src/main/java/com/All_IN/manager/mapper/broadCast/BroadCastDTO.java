package com.All_IN.manager.mapper.broadCast;

import com.All_IN.manager.domain.broadCast.BroadCast;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BroadCastDTO {

    private Long broadCastId;
    private LocalDateTime startTime;


    public BroadCastDTO(BroadCast broadCast) {
        this.broadCastId = broadCast.getId();
        this.startTime = broadCast.getSqlDateTime().getCreateAt();
    }

}
