package com.All_IN.manager.domain.room;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
@Getter
@NoArgsConstructor
public class ScheduleVO {

    @DateTimeFormat(pattern = "HH::mm")
    @Column(name = "room_info_start_time")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH::mm")
    @Column(name = "room_info_end_time")
    private LocalTime endTime;

    public ScheduleVO(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Profile("test")
    public String getStartTime_test() {
        return startTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    @Profile("test")
    public String getEndTime_test() {
        return endTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
