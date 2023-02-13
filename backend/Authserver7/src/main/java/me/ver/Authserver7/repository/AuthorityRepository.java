package me.ver.Authserver7.repository;

import me.ver.Authserver7.domain.Authority;
import me.ver.Authserver7.domain.AuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityEnum> {
    Optional<Authority> findByAuthorityStatus(AuthorityEnum authorityStatus);
}