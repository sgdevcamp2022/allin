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
import com.All_IN.manager.mapper.room.RoomInfoDTO;
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

        Publisher publisher = publisherValidateService.validatePublisher(memberId, PublisherValidateIdType.PUBLISHER);

        roomService.save(publisher, roomInfoRequest);
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

        RoomInfoRequest roomInfoRequest = new RoomInfoRequest();
        roomInfoRequest.setTitle("test title");
        roomInfoRequest.setDescription("test description");
        roomInfoRequest.setScheduleVO(new ScheduleVO(LocalTime.now(), LocalTime.now().plusHours(1L)));

        Publisher publisher = publisherValidateService.validatePublisher(memberId, PublisherValidateIdType.PUBLISHER);

        // When
        mockRoomService.save(publisher, roomInfoRequest);

        // Then
        Mockito.verify(mockRoomService, Mockito.times(1)).save(publisher, roomInfoRequest);
    }

    @Test
    void editRoomInfo() {
        //Given
        RoomInfoRequest roomInfoEditDTO = new RoomInfoRequest();
        roomInfoEditDTO.setTitle("edit test title");
        roomInfoEditDTO.setDescription("edit test description");
        roomInfoEditDTO.setScheduleVO(new ScheduleVO(LocalTime.now(), LocalTime.now().plusHours(2L)));

        //When
        roomService.editRoomInfo(roomId, roomInfoEditDTO);

        //Then
        RoomInfoDTO roomInfoDTO = roomService.browseRoomInfo(roomId);

        Assertions.assertThat(roomInfoDTO.getTitle()).isEqualTo(roomInfoEditDTO.getTitle());
        Assertions.assertThat(roomInfoDTO.getDescription()).isEqualTo(roomInfoEditDTO.getDescription());
        Assertions.assertThat(roomInfoDTO.getStartTime().toString()).isEqualTo(roomInfoEditDTO.getScheduleVO().getStartTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        Assertions.assertThat(roomInfoDTO.getEndTime().toString()).contains(roomInfoEditDTO.getScheduleVO().getEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    @Test
    void browseRoomInfo() {
        //Given

        //When
        RoomInfoDTO roomInfoDTO = roomService.browseRoomInfo(roomId);

        //Then
        Assertions.assertThat(roomInfoDTO.getTitle()).isEqualTo(roomInfoRequest.getTitle());
        Assertions.assertThat(roomInfoDTO.getDescription()).isEqualTo(roomInfoRequest.getDescription());
        Assertions.assertThat(roomInfoDTO.getStartTime().toString()).isEqualTo(
            roomInfoRequest.getScheduleVO().getStartTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        Assertions.assertThat(roomInfoDTO.getEndTime().toString()).contains(
            roomInfoRequest.getScheduleVO().getEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

}