package com.university.java_lecture_3.repository;

import com.university.java_lecture_3.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

//    List<Event> findFilteredEvents(
//            Long userId,
//            LocalDateTime startDate,
//            LocalDateTime endDate,
//            String location,
//            Boolean onlyWithFreePlaces,
//            Boolean onlyUpcoming
//    );

    @Query("""
            SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END
            FROM Event e
            WHERE e.id = :id AND e.eventTime > CURRENT_TIMESTAMP
            """)
    boolean isRelevant(@Param("id") Long id);

}
