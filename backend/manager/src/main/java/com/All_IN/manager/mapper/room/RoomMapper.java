package com.All_IN.manager.mapper.room;

import com.All_IN.manager.domain.room.RoomInfo;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public RoomInfoDTO from(RoomInfo roomInfo) {
        return new RoomInfoDTO(
            roomInfo.getId(),
            roomInfo.getTitle(),
            roomInfo.getDescription(),
            roomInfo.getScheduleVO()
        );
    }

}
