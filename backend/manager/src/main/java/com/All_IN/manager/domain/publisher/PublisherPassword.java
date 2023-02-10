package com.All_IN.manager.domain.publisher;

import com.All_IN.manager.domain.SqlDateTime;
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
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "publisher_password_table")
@Where(clause = "publisher_password_used = false")
public class PublisherPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_password_id")
    private Long id;

    @Column(name = "publisher_id")
    private Long publisherId;

    @Column(name = "publisher_password_value")
    private String value;

    @Column(name = "publisher_password_used")
    private boolean used;

    @Embedded
    private SqlDateTime.createAt sqlDateTime;


    private PublisherPassword(Long publisherId) {
        this.publisherId = publisherId;
        this.value = UUID.randomUUID().toString();
        this.used = false;
        this.sqlDateTime = new SqlDateTime.createAt();
    }

    public static PublisherPassword relatedFrom(Long publisherId) {
        return new PublisherPassword(publisherId);
    }


    public void use() {
        used = true;
    }

    public boolean checkPassword(String publisherPassword) {
        return value.equals(publisherPassword);
    }

}
