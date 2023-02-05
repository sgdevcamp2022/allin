package com.All_IN.manager.service.room;


import com.All_IN.manager.domain.publisher.PublisherRepository;
import com.All_IN.manager.domain.room.RoomInfoRepository;
import com.All_IN.manager.domain.room.RoomRepository;
import com.All_IN.manager.domain.room.ScheduleVO;
import com.All_IN.manager.service.publisher.PublisherService;
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
    void save() {
        // Given
        memberId++;
        publisherService.save(memberId);

        RoomInfoDTO roomInfoDTO = new RoomInfoDTO();
        roomInfoDTO.setTitle("test title");
        roomInfoDTO.setDescription("test description");
        roomInfoDTO.setScheduleVO(new ScheduleVO(LocalTime.now(), LocalTime.now().plusHours(1L)));

        // When
        mockRoomService.save(memberId, roomInfoDTO);

        // Then
        Mockito.verify(mockRoomService, Mockito.times(1)).save(memberId, roomInfoDTO);
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