package com.All_IN.manager.service.room;

import com.All_IN.manager.domain.publisher.Publisher;
import com.All_IN.manager.domain.publisher.PublisherRepository;
import com.All_IN.manager.domain.room.RoomInfoRepository;
import com.All_IN.manager.domain.room.RoomRepository;
import com.All_IN.manager.domain.room.ScheduleVO;
import com.All_IN.manager.service.publisher.PublisherService;
import com.All_IN.manager.service.publisher.PublisherValidateIdType;
import com.All_IN.manager.service.publisher.PublisherValidateService;
import com.All_IN.manager.web.dto.RoomInfoRequest;
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
    PublisherValidateService publisherValidateService;

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomInfoRepository roomInfoRepository;


    static Long roomId = 1L;

    static Long memberId = 1L;

    static RoomInfoRequest roomInfoRequest = setRoomInfoDTO();


    static RoomInfoRequest setRoomInfoDTO() {
        RoomInfoRequest roomInfoRequest = new RoomInfoRequest();
        roomInfoRequest.setTitle("test title");
        roomInfoRequest.setDescription("test description");
        roomInfoRequest.setScheduleVO(new ScheduleVO(LocalTime.now(), LocalTime.now().plusHours(1L)));
        return roomInfoRequest;
    }

    @BeforeEach
    void data() {
        publisherService.save(memberId);

        Long validatePublisherId = publisherValidateService.validatePublisher(memberId, PublisherValidateIdType.MEMBER);

        roomService.save(validatePublisherId, roomInfoRequest);
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
    void save_ALREADY_HAVE_ROOM() {
        // Given
        memberId++;
        publisherService.save(memberId);
        Long validatePublisherId = publisherValidateService.validatePublisher(memberId, PublisherValidateIdType.PUBLISHER);
        roomService.save(validatePublisherId, roomInfoRequest);
        roomId++;


        // When
        Assertions.assertThatThrownBy(() -> roomService.save(validatePublisherId, roomInfoRequest))
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
