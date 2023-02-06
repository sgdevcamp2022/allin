package com.All_IN.manager.service.room;


import com.All_IN.manager.domain.publisher.Publisher;
import com.All_IN.manager.domain.publisher.PublisherRepository;
import com.All_IN.manager.domain.room.RoomInfoRepository;
import com.All_IN.manager.domain.room.RoomRepository;
import com.All_IN.manager.domain.room.ScheduleVO;
import com.All_IN.manager.service.publisher.PublisherService;
import com.All_IN.manager.service.publisher.PublisherValidateIdType;
import com.All_IN.manager.service.publisher.PublisherValidateService;
import com.All_IN.manager.service.room.dto.RoomInfoDTO;
import com.All_IN.manager.service.room.dto.RoomInfoResponse;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@Slf4j
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RoomServiceTest {

    @Autowired
    RoomService roomService;

    @Mock
    RoomService mockRoomService;

    @Autowired
    PublisherService publisherService;

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    PublisherValidateService publisherValidateService;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomInfoRepository roomInfoRepository;


    static Long roomId = 1L;

    static Long memberId = 1L;

    static RoomInfoDTO roomInfoDTO = setRoomInfoDTO();


    static RoomInfoDTO setRoomInfoDTO() {
        RoomInfoDTO roomInfoDTO = new RoomInfoDTO();
        roomInfoDTO.setTitle("test title");
        roomInfoDTO.setDescription("test description");
        roomInfoDTO.setScheduleVO(new ScheduleVO(LocalTime.now(), LocalTime.now().plusHours(1L)));
        return roomInfoDTO;
    }


    @BeforeEach
    void data() {
        publisherService.save(memberId);

        Publisher publisher = publisherValidateService.validatePublisher(memberId, PublisherValidateIdType.PUBLISHER);

        roomService.save(publisher, roomInfoDTO);
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
    void save() {
        // Given
        memberId++;
        publisherService.save(memberId);

        RoomInfoDTO roomInfoDTO = new RoomInfoDTO();
        roomInfoDTO.setTitle("test title");
        roomInfoDTO.setDescription("test description");
        roomInfoDTO.setScheduleVO(new ScheduleVO(LocalTime.now(), LocalTime.now().plusHours(1L)));

        Publisher publisher = publisherValidateService.validatePublisher(memberId, PublisherValidateIdType.PUBLISHER);

        // When
        mockRoomService.save(publisher, roomInfoDTO);

        // Then
        Mockito.verify(mockRoomService, Mockito.times(1)).save(publisher, roomInfoDTO);
    }

    @Test
    void editRoomInfo() {
        //Given
        RoomInfoDTO roomInfoEditDTO = new RoomInfoDTO();
        roomInfoEditDTO.setTitle("edit test title");
        roomInfoEditDTO.setDescription("edit test description");
        roomInfoEditDTO.setScheduleVO(new ScheduleVO(LocalTime.now(), LocalTime.now().plusHours(2L)));

        //When
        roomService.editRoomInfo(roomId, roomInfoEditDTO);

        //Then
        RoomInfoResponse roomInfoResponse = roomService.browseRoomInfo(roomId);

        Assertions.assertThat(roomInfoResponse.getTitle()).isEqualTo(roomInfoEditDTO.getTitle());
        Assertions.assertThat(roomInfoResponse.getDescription()).isEqualTo(roomInfoEditDTO.getDescription());
        Assertions.assertThat(roomInfoResponse.getStartTime().toString()).isEqualTo(roomInfoEditDTO.getScheduleVO().getStartTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        Assertions.assertThat(roomInfoResponse.getEndTime().toString()).contains(roomInfoEditDTO.getScheduleVO().getEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    @Test
    void browseRoomInfo() {
        //Given

        //When
        RoomInfoResponse roomInfoResponse = roomService.browseRoomInfo(roomId);

        //Then
        Assertions.assertThat(roomInfoResponse.getTitle()).isEqualTo(roomInfoDTO.getTitle());
        Assertions.assertThat(roomInfoResponse.getDescription()).isEqualTo(roomInfoDTO.getDescription());
        Assertions.assertThat(roomInfoResponse.getStartTime().toString()).isEqualTo(roomInfoDTO.getScheduleVO().getStartTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        Assertions.assertThat(roomInfoResponse.getEndTime().toString()).contains(roomInfoDTO.getScheduleVO().getEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

}