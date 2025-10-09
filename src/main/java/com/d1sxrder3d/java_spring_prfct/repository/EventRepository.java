package com.d1sxrder3d.java_spring_prfct.repository;

import com.d1sxrder3d.java_spring_prfct.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e JOIN e.participants u WHERE u.id = :userId")
    List<Event> findByParticipantId(@Param("userId") Long userId);

    @Query("SELECT e FROM Event e " +
            "WHERE (:userId IS NULL OR e IN (SELECT e2 FROM Event e2 JOIN e2.participants u WHERE u.id = :userId)) " +
            "AND (:location IS NULL OR e.location ILIKE %:location%) " +
            "AND (:minDate IS NULL OR e.eventDateTime >= :minDate) " +
            "AND (:maxDate IS NULL OR e.eventDateTime <= :maxDate) " +
            "AND (:onlyUpcoming IS FALSE OR e.eventDateTime > CURRENT_TIMESTAMP) " +
            "AND (:onlyWithSpaces IS FALSE OR e.maxParticipants IS NULL OR e.maxParticipants > SIZE(e.participants))")
    List<Event> findFiltered(
            @Param("userId") Long userId,
            @Param("location") String location,
            @Param("minDate") LocalDateTime minDate,
            @Param("maxDate") LocalDateTime maxDate,
            @Param("onlyUpcoming") Boolean onlyUpcoming,
            @Param("onlyWithSpaces") Boolean onlyWithSpaces
    );
}