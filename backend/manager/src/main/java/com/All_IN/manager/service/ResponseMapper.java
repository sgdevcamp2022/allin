package com.All_IN.manager.service;

import com.All_IN.manager.domain.room.RoomInfo;
import com.All_IN.manager.service.room.dto.RoomInfoResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseMapper {

    public static RoomInfoResponse valueOf(RoomInfo roomInfo) {
        return new RoomInfoResponse(
            roomInfo.getId(),
            roomInfo.getTitle(),
            roomInfo.getDescription(),
            roomInfo.getScheduleVO()
        );
    }

}
