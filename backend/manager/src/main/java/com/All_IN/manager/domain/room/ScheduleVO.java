package com.All_IN.manager.domain.room;

import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
}
