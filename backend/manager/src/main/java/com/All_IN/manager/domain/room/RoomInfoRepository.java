package com.All_IN.manager.domain.room;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomInfoRepository extends JpaRepository<RoomInfo, Long> {

    @Query(value = "select ri from RoomInfo ri where ri.roomId = :roomId")
    Optional<RoomInfo> findByRoom(@Param("roomId") Long roomId);

}
