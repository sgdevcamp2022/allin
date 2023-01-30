package com.All_IN.manager.domain.room;

import com.All_IN.manager.domain.SqlDateTime;
import com.All_IN.manager.service.room.dto.RoomInfoDTO;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "room_info_table")
@Getter
@NoArgsConstructor
@Where(clause = "DELETE_AT IS NULL")
@SQLDelete(sql = "UPDATE ROOM_INFO_TABLE SET DELETE_AT = CURRENT_TIMESTAMP where ROOM_INFO_ID = ?")
public class RoomInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_info_id")
    private Long id;

    @Column(name = "room_info_title")
    private String title;

    @Column(name = "room_info_description")
    private String description;

    @Embedded
    private ScheduleVO scheduleVO;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Embedded
    private SqlDateTime.modifiedAndDeleteAt sqlDatetime;

    private RoomInfo(Room room, String title, String description, ScheduleVO scheduleVO) {
        this.room = room;
        this.title = title;
        this.description = description;
        this.scheduleVO = scheduleVO;
        this.sqlDatetime = new SqlDateTime.modifiedAndDeleteAt();
    }

    public static RoomInfo create(Room room, RoomInfoDTO roomInfoDTO) {
        return new RoomInfo(
            room,
            roomInfoDTO.getTitle(),
            roomInfoDTO.getDescription(),
            roomInfoDTO.getScheduleVO()
        );
    }

    public void updateInfo(RoomInfoDTO roomInfoDTO) {
        this.title = roomInfoDTO.getTitle();
        this.description = roomInfoDTO.getDescription();
        this.scheduleVO = roomInfoDTO.getScheduleVO();
        this.sqlDatetime = sqlDatetime.modify();
    }

}
