package com.All_IN.manager.domain.broadCast;

import com.All_IN.manager.domain.publisher.Publisher;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BroadCastRepository extends JpaRepository<BroadCast, Long> {

    @Query(nativeQuery = true,
        value =
            "select exists("
            + "select b.BROADCAST_ID from BROAD_CAST_TABLE b "
                + "where b.PUBLISHER_ID = :publisher "
                    + "and b.BROADCAST_STATE = 'LIVE'"
            + ")"
    )
    boolean existByPublisherAndStateIsLive(@Param("publisher") Publisher publisher);

    Optional<BroadCast> findByPublisherAndState(Publisher publisher, BroadCastState state);

    List<BroadCast> findAllByState(BroadCastState state);

}
