package com.All_IN.manager.domain.publisher;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherPasswordRepository extends JpaRepository<PublisherPassword, Long> {

    Optional<PublisherPassword> findByPublisher(Publisher publisher);

    Optional<PublisherPassword> findByValue(String password);

}
