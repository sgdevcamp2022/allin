package com.All_IN.manager.domain.room;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select count(r.id) > 0 from Room r where r.publisherId = :publisherId")
    boolean existsByPublisher(@Param("publisherId") Long publisherId);

    Optional<Room> findByPublisherId(Long publisherId);
}
