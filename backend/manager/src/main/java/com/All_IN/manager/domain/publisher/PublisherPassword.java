package com.All_IN.manager.domain.publisher;

import com.All_IN.manager.domain.SqlDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "publisher_password_table")
@Where(clause = "publisher_password_used = false")
public class PublisherPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_password_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(name = "publisher_password_value")
    private String value;

    @Column(name = "publisher_password_used")
    private boolean used;

    @Embedded
    private SqlDateTime.createAt sqlDateTime;

    public PublisherPassword(Publisher publisher) {
        this.publisher = publisher;
        this.value = UUID.randomUUID().toString();
        this.used = false;
        this.sqlDateTime = new SqlDateTime.createAt();
    }

    public void use() {
        this.used = true;
    }

    public boolean checkPassword(String publisher_password) {
        return this.value.equals(publisher_password);
    }
}
