package com.All_IN.manager.domain.room;

import com.All_IN.manager.domain.publisher.Publisher;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByPublisher(Publisher publisher);
}
