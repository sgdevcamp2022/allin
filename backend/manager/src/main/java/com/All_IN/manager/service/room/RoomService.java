package com.All_IN.manager.service.room;

import com.All_IN.manager.domain.room.Room;
import com.All_IN.manager.domain.room.RoomInfo;
import com.All_IN.manager.domain.room.RoomInfoRepository;
import com.All_IN.manager.domain.room.RoomRepository;
import com.All_IN.manager.domain.room.ScheduleVO;
import com.All_IN.manager.mapper.room.RoomDTO;
import com.All_IN.manager.mapper.room.RoomMapper;
import com.All_IN.manager.web.dto.RoomInfoRequest;
import com.All_IN.manager.mapper.room.RoomInfoDTO;
import com.All_IN.manager.service.room.exception.RoomServiceException;
import com.All_IN.manager.service.room.exception.RoomServiceValidateException;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomService {

    private final RoomRepository repository;
    private final RoomInfoRepository roomInfoRepository;

    private final RoomMapper mapper;


    @Transactional
    public void save(Long publisherId, RoomInfoRequest roomInfoRequest) {
        if (repository.existsByPublisher(publisherId)) {
            throw new RoomServiceValidateException(RoomServiceException.ALREADY_HAVE_ROOM);
        }
        Room room = Room.relatedFrom(publisherId);
        repository.save(room);

        roomInfoRepository.save(
            RoomInfo.relatedOf(
                room.getId(),
                roomInfoRequest.getTitle(),
                roomInfoRequest.getDescription(),
                new ScheduleVO(
                    LocalTime.parse(roomInfoRequest.getStartTime()),
                    LocalTime.parse(roomInfoRequest.getEndTime())
                )
            )
        );
    }

    @Transactional
    public void editRoomInfo(Long roomId, RoomInfoRequest roomInfoRequest) {
        Room room = repository.findById(roomId)
            .orElseThrow(
                () -> new RoomServiceValidateException(RoomServiceException.NO_MATCH_ROOM));

        RoomInfo existRoomInfo = roomInfoRepository.findByRoom(room.getId())
            .orElseThrow(
                () -> new RoomServiceValidateException(RoomServiceException.NO_ROOM_INFORMATION));

        existRoomInfo.updateInfo(
            roomInfoRequest.getTitle(),
            roomInfoRequest.getDescription(),
            new ScheduleVO(
                LocalTime.parse(roomInfoRequest.getStartTime()),
                LocalTime.parse(roomInfoRequest.getEndTime())
            )
        );
    }

    public RoomInfoDTO browseRoomInfo(Long roomId) {
        Room room = repository.findById(roomId)
            .orElseThrow(
                () -> new RoomServiceValidateException(RoomServiceException.NO_MATCH_ROOM));

        RoomInfo roomInfo = roomInfoRepository.findByRoom(room.getId())
            .orElseThrow(
                () -> new RoomServiceValidateException(RoomServiceException.NO_MATCH_ROOM_INFO));

        return mapper.from(roomInfo);
    }

    public RoomInfoDTO browseMemberRoomInfo(Long publisherId) {
        Room room = repository.findByPublisherId(publisherId).orElseThrow(
            () -> new RoomServiceValidateException(RoomServiceException.NO_MATCH_ROOM));

        RoomInfo roomInfo = roomInfoRepository.findByRoom(room.getId())
            .orElseThrow(
                () -> new RoomServiceValidateException(RoomServiceException.NO_MATCH_ROOM_INFO));

        return mapper.from(roomInfo);
    }

    public List<RoomDTO> browseRoomList() {
        List<Room> allRoom = repository.findAll();

        return mapper.from(allRoom);
    }
}
