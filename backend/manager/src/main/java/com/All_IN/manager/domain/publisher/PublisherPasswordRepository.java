package com.All_IN.manager.domain.publisher;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PublisherPasswordRepository extends JpaRepository<PublisherPassword, Long> {

    @Query(value = "select pp from PublisherPassword pp where pp.publisherId = :publisherId")
    Optional<PublisherPassword> findByPublisher(@Param("publisherId")Long publisherId);

    Optional<PublisherPassword> findByValue(String password);

}
