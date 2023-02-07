package com.All_IN.manager.mapper.room;

import com.All_IN.manager.domain.room.ScheduleVO;
import java.time.LocalTime;
import lombok.Data;

@Data
public class RoomInfoDTO {

    private Long id;

    private String title;

    private String description;

    private LocalTime startTime;

    private LocalTime endTime;


    public RoomInfoDTO(Long id, String title, String description, ScheduleVO scheduleVO) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = scheduleVO.getStartTime();
        this.endTime = scheduleVO.getEndTime();
    }

}
