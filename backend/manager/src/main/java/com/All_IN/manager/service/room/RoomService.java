package com.All_IN.manager.service.room;

import com.All_IN.manager.domain.publisher.Publisher;
import com.All_IN.manager.domain.room.Room;
import com.All_IN.manager.domain.room.RoomInfo;
import com.All_IN.manager.domain.room.RoomInfoRepository;
import com.All_IN.manager.domain.room.RoomRepository;
import com.All_IN.manager.service.ResponseMapper;
import com.All_IN.manager.service.room.dto.RoomInfoDTO;
import com.All_IN.manager.service.room.dto.RoomInfoResponse;
import com.All_IN.manager.service.room.exception.RoomServiceException;
import com.All_IN.manager.service.room.exception.RoomServiceValidateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomService {

    private final RoomRepository repository;
    private final RoomInfoRepository roomInfoRepository;


    @Transactional
    public void save(Publisher publisher, RoomInfoDTO roomInfoDTO) {
        if (repository.existsByPublisher(publisher)) {
            throw new RoomServiceValidateException(RoomServiceException.ALREADY_HAVE_ROOM);
        }
        Room room = Room.from(publisher);
        repository.save(room);

        roomInfoRepository.save(RoomInfo.of(room, roomInfoDTO));
    }

    @Transactional
    public void editRoomInfo(Long roomId, RoomInfoDTO roomInfoDTO) {
        Room room = repository.findById(roomId)
            .orElseThrow(
                () -> new RoomServiceValidateException(RoomServiceException.NO_MATCH_ROOM));

        RoomInfo existRoomInfo = roomInfoRepository.findByRoom(room)
            .orElseThrow(
                () -> new RoomServiceValidateException(RoomServiceException.NO_ROOM_INFORMATION));

        existRoomInfo.updateInfo(roomInfoDTO);
    }

    public RoomInfoResponse browseRoomInfo(Long roomId) {
        Room room = repository.findById(roomId)
            .orElseThrow(
                () -> new RoomServiceValidateException(RoomServiceException.NO_MATCH_ROOM));

        RoomInfo roomInfo = roomInfoRepository.findByRoom(room)
            .orElseThrow(
                () -> new RoomServiceValidateException(RoomServiceException.NO_MATCH_ROOM_INFO));

        return ResponseMapper.valueOf(roomInfo);
    }

}
