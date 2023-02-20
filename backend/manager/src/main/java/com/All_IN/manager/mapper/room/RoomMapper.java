package com.All_IN.manager.mapper.room;

import com.All_IN.manager.domain.room.Room;
import com.All_IN.manager.domain.room.RoomInfo;
import java.util.List;
import java.util.stream.Collectors;
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

    public List<RoomDTO> from(List<Room> rooms) {
        return rooms.stream()
            .map(RoomDTO::new)
            .collect(Collectors.toList());
    }
}
