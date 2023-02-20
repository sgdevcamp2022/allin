package com.All_IN.manager.web.dto;

import com.All_IN.manager.mapper.room.RoomInfoDTO;
import java.time.LocalTime;
import lombok.Data;

@Data
public class RoomInfoResponse {

    private Long id;

    private String title;

    private String description;

    private LocalTime startTime;

    private LocalTime endTime;


    public RoomInfoResponse(RoomInfoDTO roomInfoDTO) {
        this.id = roomInfoDTO.getId();
        this.title = roomInfoDTO.getTitle();
        this.description = roomInfoDTO.getDescription();
        this.startTime = roomInfoDTO.getStartTime();
        this.endTime = roomInfoDTO.getEndTime();
    }

}
