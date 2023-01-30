package com.All_IN.manager.service.room.dto;

import com.All_IN.manager.domain.room.ScheduleVO;
import lombok.Data;

@Data
public class RoomInfoDTO {

    private String title;

    private String description;

    private ScheduleVO scheduleVO;

}
