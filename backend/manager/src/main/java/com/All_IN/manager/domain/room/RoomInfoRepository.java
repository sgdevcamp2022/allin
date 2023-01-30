package com.All_IN.manager.domain.room;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomInfoRepository extends JpaRepository<RoomInfo, Long> {

    Optional<RoomInfo> findByRoom(Room room);
}
