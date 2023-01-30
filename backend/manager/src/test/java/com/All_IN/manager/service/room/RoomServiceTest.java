package com.All_IN.manager.service.room;


import com.All_IN.manager.domain.room.ScheduleVO;
import com.All_IN.manager.service.publisher.PublisherService;
import com.All_IN.manager.service.room.dto.RoomInfoDTO;
import com.All_IN.manager.service.room.dto.RoomInfoResponse;
import java.time.LocalTime;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @Autowired
    PublisherService publisherService;

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
        publisherService.clear();
        memberId++;

        roomService.clear_all();
        roomId++;
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
        roomService.save(memberId, roomInfoDTO);

        // Then
        roomId++;
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
        Assertions.assertThat(roomInfoResponse.getStartTime().toString()).isEqualTo(roomInfoEditDTO.getScheduleVO().getStartTime_test());
        Assertions.assertThat(roomInfoResponse.getEndTime().toString()).contains(roomInfoEditDTO.getScheduleVO().getEndTime_test());
    }

    @Test
    void browseRoomInfo() {
        //Given

        //When
        RoomInfoResponse roomInfoResponse = roomService.browseRoomInfo(roomId);

        //Then
        Assertions.assertThat(roomInfoResponse.getTitle()).isEqualTo(roomInfoDTO.getTitle());
        Assertions.assertThat(roomInfoResponse.getDescription()).isEqualTo(roomInfoDTO.getDescription());
        Assertions.assertThat(roomInfoResponse.getStartTime().toString()).isEqualTo(roomInfoDTO.getScheduleVO().getStartTime_test());
        Assertions.assertThat(roomInfoResponse.getEndTime().toString()).contains(roomInfoDTO.getScheduleVO().getEndTime_test());
    }
}