package com.All_IN.manager.service.room;

import com.All_IN.manager.domain.publisher.PublisherRepository;
import com.All_IN.manager.domain.room.RoomInfoRepository;
import com.All_IN.manager.domain.room.RoomRepository;
import com.All_IN.manager.domain.room.ScheduleVO;
import com.All_IN.manager.service.publisher.PublisherService;
import com.All_IN.manager.service.publisher.exception.PublisherServiceException;
import com.All_IN.manager.service.room.dto.RoomInfoDTO;
import com.All_IN.manager.service.room.dto.RoomInfoResponse;
import com.All_IN.manager.service.room.exception.RoomServiceException;
import java.time.LocalTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class RoomServiceTestException {

    @Autowired
    RoomService roomService;

    @Autowired
    PublisherService publisherService;

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomInfoRepository roomInfoRepository;

    private static Long roomId = 1L;
    private static Long memberId = 1L;

    private static RoomInfoDTO roomInfoDTO = setRoomInfoDTO();

    private static RoomInfoDTO setRoomInfoDTO() {
        RoomInfoDTO roomInfoDTO = new RoomInfoDTO();
        roomInfoDTO.setTitle("test title");
        roomInfoDTO.setDescription("test description");
        roomInfoDTO.setScheduleVO(new ScheduleVO(LocalTime.now(), LocalTime.now().plusHours(1L)));
        return roomInfoDTO;
    }

    @BeforeEach
    public void save_data() {
        publisherService.save(memberId);

        roomService.save(memberId, roomInfoDTO);
    }

    @AfterEach
    void clear() {
        clearData();
        memberId++;
        roomId++;
    }

    private void clearData() {
        clearPublisherData();
        clearRoomData();
    }

    private void clearPublisherData() {
        publisherRepository.deleteAll();
    }

    private void clearRoomData() {
        roomRepository.deleteAll();
        roomInfoRepository.deleteAll();
    }

    @Test
    void save_NO_SUCH_PUBLISHER() {
        Assertions.assertThatThrownBy(() -> roomService.save(999L, roomInfoDTO))
            .hasMessage(PublisherServiceException.NO_SUCH_PUBLISHER.getMessage());
    }

    @Test
    void save_ALREADY_HAVE_ROOM() {
        // Given
        memberId++;
        publisherService.save(memberId);
        roomService.save(memberId, roomInfoDTO);
        roomId++;


        // When
        Assertions.assertThatThrownBy(() -> roomService.save(memberId, roomInfoDTO))
            .hasMessage(RoomServiceException.ALREADY_HAVE_ROOM.getMessage());

        // Then

    }

    @Test
    void editRoomInfo_NO_MATCH_ROOM() {
        //Given
        clearRoomData();

        //When
        Assertions.assertThatThrownBy(() -> roomService.editRoomInfo(roomId, null))
            .hasMessage(RoomServiceException.NO_MATCH_ROOM.getMessage());

        //Then

    }

    @Test
    void editRoomInfo_NO_ROOM_INFORMATION() {
        //Given
        roomInfoRepository.deleteAll();

        //When
        Assertions.assertThatThrownBy(() -> roomService.editRoomInfo(roomId, null))
            .hasMessage(RoomServiceException.NO_ROOM_INFORMATION.getMessage());

        //Then

    }

    @Test
    void browseRoomInfo_NO_MATCH_ROOM() {
        //Given
        clearRoomData();

        //When
        Assertions.assertThatThrownBy(() -> roomService.browseRoomInfo(roomId))
            .hasMessage(RoomServiceException.NO_MATCH_ROOM.getMessage());

        //Then

    }

    @Test
    void browseRoomInfo_NO_MATCH_ROOM_INFO() {
        //Given
        roomInfoRepository.deleteAll();

        //When
        Assertions.assertThatThrownBy(() -> roomService.browseRoomInfo(roomId))
            .hasMessage(RoomServiceException.NO_MATCH_ROOM_INFO.getMessage());

        //Then

    }
}
