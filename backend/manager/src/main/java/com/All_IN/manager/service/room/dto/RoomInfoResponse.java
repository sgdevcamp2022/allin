package com.All_IN.manager.service.room.dto;

import com.All_IN.manager.domain.room.ScheduleVO;
import java.time.LocalTime;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class RoomInfoResponse {

    private Long id;

    private String title;

    private String description;

    private LocalTime startTime;

    private LocalTime endTime;


    public RoomInfoResponse(Long id, String title, String description, ScheduleVO scheduleVO) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = scheduleVO.getStartTime();
        this.endTime = scheduleVO.getEndTime();
    }

}
