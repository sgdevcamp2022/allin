package com.All_IN.manager.web.dto;

import com.All_IN.manager.mapper.room.RoomDTO;
import java.util.List;
import lombok.Data;

@Data
public class AllRoomResponse {

    List<RoomDTO> roomList;

    public AllRoomResponse(List<RoomDTO> roomDTOList) {
        this.roomList = roomDTOList;
    }
}
