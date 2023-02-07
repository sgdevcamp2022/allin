package com.All_IN.manager.domain.room;

import com.All_IN.manager.domain.SqlDateTime;
import com.All_IN.manager.domain.publisher.Publisher;
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
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "room_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "DELETE_AT IS NULL")
@SQLDelete(sql = "UPDATE ROOM_TABLE SET DELETE_AT = CURRENT_TIMESTAMP where ROOM_ID = ?")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Embedded
    private SqlDateTime.createAndDeleteAt sqlDateTime;


    private Room(Publisher publisher) {
        this.publisher = publisher;
        this.sqlDateTime = new SqlDateTime.createAndDeleteAt();
    }

    public static Room from(Publisher publisher) {
        return new Room(publisher);
    }

}
