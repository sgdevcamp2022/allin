package com.All_IN.manager.web.dto;

import com.All_IN.manager.domain.room.ScheduleVO;
import lombok.Data;

@Data
public class RoomInfoRequest {

    private String title;

    private String description;

    private ScheduleVO scheduleVO;

}
