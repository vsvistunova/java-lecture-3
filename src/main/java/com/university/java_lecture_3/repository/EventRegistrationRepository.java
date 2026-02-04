package com.university.java_lecture_3.repository;

import com.university.java_lecture_3.model.entity.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration,Long> {
}
