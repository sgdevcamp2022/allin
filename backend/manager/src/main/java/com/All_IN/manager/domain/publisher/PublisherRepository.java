package com.All_IN.manager.domain.publisher;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Optional<Publisher> findByMemberId(Long memberId);

    Optional<Publisher> findByKey(String key);

}
