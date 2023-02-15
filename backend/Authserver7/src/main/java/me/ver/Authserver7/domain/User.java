package me.ver.Authserver7.domain;

import java.time.LocalDateTime;
import lombok.*;
import me.ver.Authserver7.dto.UserUpdateDto;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String userName;
    @Column
    private String password;
    @Column(unique = true, length = 8)
    private String nickName;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @ManyToMany
    @Column
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name="user_id",referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_status",referencedColumnName = "authority_status")})
    private Set<Authority> authorities = new HashSet<>();

    @Builder
    public User(String email, String userName, String password, String nickName, Set<Authority> authorities, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.authorities = authorities;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public void updateMember(UserUpdateDto dto, PasswordEncoder passwordEncoder) {
        if(dto.getPassword() != null) this.password = passwordEncoder.encode(dto.getPassword());
        if(dto.getUserName() != null) this.userName = dto.getUserName();
        if(dto.getNickName()!= null) this.nickName = dto.getNickName();
    }
}
