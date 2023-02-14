package com.All_IN.manager.domain.broadCast;

import com.All_IN.manager.domain.SqlDateTime.createAndModifiedAt;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "broad_cast_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BroadCast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broadcast_id")
    private Long id;

    @Column(name = "broadcast_state")
    @Enumerated(value = EnumType.STRING)
    private BroadCastState state;

    @Embedded
    private createAndModifiedAt sqlDateTime;

    @Column(name = "publisher_id")
    private Long publisherId;

    private BroadCast(Long publisherId) {
        this.state = BroadCastState.LIVE;
        this.sqlDateTime = new createAndModifiedAt();
        this.publisherId = publisherId;
    }

    public static BroadCast relatedFrom(Long publisherId) {
        return new BroadCast(publisherId);
    }


    public void end() {
        state = BroadCastState.end();
        sqlDateTime = sqlDateTime.modify();
    }

}
