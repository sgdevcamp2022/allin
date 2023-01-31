package com.All_IN.manager.domain.publisher;

import com.All_IN.manager.domain.SqlDateTime;
import com.All_IN.manager.utils.Md5;
import java.util.UUID;
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
@Table(name = "publisher_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Where(clause = "DELETE_AT IS NULL")
@SQLDelete(sql = "UPDATE PUBLISHER_TABLE SET DELETE_AT = CURRENT_TIMESTAMP where PUBLISHER_ID = ?")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Long id;

    private Long memberId;

    @Column(name = "publisher_key")
    private String key;

    @Embedded
    private SqlDateTime.All sqlDateTime;

    public Publisher(Long memberId) {
        this.memberId = memberId;
        this.key = Md5.encode(UUID.randomUUID().toString());
        this.sqlDateTime = new SqlDateTime.All();
    }

    public void updateKey() {
        this.key = Md5.encode(UUID.randomUUID().toString());
        this.sqlDateTime = sqlDateTime.modify();
    }
}
