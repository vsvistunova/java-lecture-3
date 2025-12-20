package com.university.java_lecture_3.repository;

import com.university.java_lecture_3.model.EventRegistration;
import com.university.java_lecture_3.projection.UserSummaryProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {

    @EntityGraph(attributePaths = {"user", "user.group", "event"})
    List<EventRegistration> findAllByUserId(Long userId, Pageable pageable);

    @Query("""
                SELECT u.id AS id,
                    u.name AS name,
                    u.age AS age,
                    u.email AS email,
                    u.role AS role,
                    g.name AS groupName,
                    COUNT(er.id) AS eventsCount
                FROM EventRegistration er
                JOIN er.user u ON u.id = er.user.id
                LEFT JOIN u.group g ON g.id = u.group.id
                WHERE er.event.id = :eventId
                GROUP BY u.id, g.name
            """)
    List<UserSummaryProjection> findUsersByEventId(Long eventId, Pageable pageable);

    boolean existsByUserIdAndEventId(Long userId, Long eventId);

}
