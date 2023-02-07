package me.ver.Authserver7.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {

    @Id
    @Column(name = "authority_status")
    @Enumerated(EnumType.STRING)
    private AuthorityEnum authorityStatus;

    public String getAuthorityStatus() {
        return this.authorityStatus.toString();
    }

    @Builder
    public Authority(AuthorityEnum authorityStatus) {
        this.authorityStatus = authorityStatus;
    }
}
