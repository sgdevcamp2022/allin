package com.All_IN.manager.domain.broadCast;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BroadCastRepository extends JpaRepository<BroadCast, Long> {

    @Query("select count (b.id) > 0 from BroadCast b where b.publisherId = :publisherId and b.state = 'LIVE' ")
        boolean existByPublisherAndStateIsLive(@Param("publisherId") Long publisherId);

    @Query("select bc from BroadCast bc where bc.publisherId = :publisherId and bc.state = :state")
    Optional<BroadCast> findByPublisherAndState(@Param("publisherId") Long publisherId, @Param("state") BroadCastState state);

    List<BroadCast> findAllByState(BroadCastState state);

}
