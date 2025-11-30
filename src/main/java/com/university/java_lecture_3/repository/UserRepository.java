package com.university.java_lecture_3.repository;

import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.projection.UserSummaryProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            SELECT u.id AS id,
                   u.name AS name,
                   u.age AS age,
                   u.email AS email,
                   u.role AS role,
                   g.name AS groupName,
                   COUNT(er.id) AS eventsCount
            FROM User u
            LEFT JOIN u.group g ON g.id = u.group.id
            LEFT JOIN u.eventRegistrations er ON er.user.id = u.id
            GROUP BY u.id, g.name
            """)
    List<UserSummaryProjection> findAllUserSummaries(Pageable pageable);

    @Query("""
            SELECT u.id AS id,
                   u.name AS name,
                   u.age AS age,
                   u.email AS email,
                   u.role AS role,
                   g.name AS groupName,
                   COUNT(er.id) AS eventsCount
            FROM User u
            LEFT JOIN u.group g ON g.id = u.group.id
            LEFT JOIN u.eventRegistrations er ON er.user.id = u.id
            WHERE age BETWEEN :minAge AND :maxAge
            GROUP BY u.id, g.name
            """)
    List<UserSummaryProjection> findAllByAgeBetween(int minAge, int maxAge, Pageable pageable);

    @EntityGraph(attributePaths = {"group", "eventRegistrations", "eventRegistrations.event"})
    Optional<User> findWithDetailsById(Long id);

}
