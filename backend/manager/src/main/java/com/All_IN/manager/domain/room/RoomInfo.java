package com.All_IN.manager.domain.room;

import com.All_IN.manager.domain.SqlDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "room_info_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "DELETE_AT != MODIFIED_AT")
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

    @Column(name = "room_id")
    private Long roomId;

    @Embedded
    private SqlDateTime.modifiedAndDeleteAt sqlDatetime;


    private RoomInfo(Long roomId, String title, String description, ScheduleVO scheduleVO) {
        this.roomId = roomId;
        this.title = title;
        this.description = description;
        this.scheduleVO = scheduleVO;
        this.sqlDatetime = new SqlDateTime.modifiedAndDeleteAt();
    }

    public static RoomInfo relatedOf(Long roomId, String title, String description, ScheduleVO scheduleVO) {
        return new RoomInfo(
            roomId,
            title,
            description,
            scheduleVO
        );
    }


    public void updateInfo(String updateTitle, String updateDescription, ScheduleVO updateScheduleVO) {
        title = updateTitle;
        description = updateDescription;
        scheduleVO = updateScheduleVO;
        sqlDatetime = sqlDatetime.modify();
    }

}
