package com.university.java_lecture_3.repository;

import com.university.java_lecture_3.model.EventRegistration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {

    @EntityGraph(attributePaths = {"user", "user.group", "event"})
    List<EventRegistration> findAllByUserId(Long userId, Pageable pageable);

    boolean existsByUserIdAndEventId(Long userId, Long eventId);

}
