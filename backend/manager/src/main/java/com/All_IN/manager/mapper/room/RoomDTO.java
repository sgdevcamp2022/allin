package com.All_IN.manager.mapper.room;

import com.All_IN.manager.domain.room.Room;
import lombok.Data;

@Data
public class RoomDTO {

    private Long id;

    private Long publisherId;

    public RoomDTO(Room room) {
        this.id = room.getId();
        this.publisherId = room.getPublisherId();
    }

}
