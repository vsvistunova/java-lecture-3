package com.university.java_lecture_3.service;

import com.university.java_lecture_3.dto.response.EventRegistrationResponse;
import com.university.java_lecture_3.dto.response.UserSummaryResponse;
import com.university.java_lecture_3.exception.ConflictException;
import com.university.java_lecture_3.mapper.EventRegistrationMapper;
import com.university.java_lecture_3.mapper.UserMapper;
import com.university.java_lecture_3.model.Event;
import com.university.java_lecture_3.model.EventRegistration;
import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.projection.UserSummaryProjection;
import com.university.java_lecture_3.repository.EventRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventRegistrationService {

    private final EventRegistrationRepository eventRegistrationRepository;
    private final EventRegistrationMapper eventRegistrationMapper;
    private final UserService userService;
    private final UserMapper userMapper;
    private final EventService eventService;

    public List<EventRegistrationResponse> getAllByUserId(Long userId, Pageable pageable) {
        List<EventRegistration> registrations = eventRegistrationRepository.findAllByUserId(userId, pageable);

        return eventRegistrationMapper.toEventRegistrationResponseList(registrations);
    }

    public List<UserSummaryResponse> getParticipantsByEventId(Long eventId, Pageable pageable) {
        List<UserSummaryProjection> registrations = eventRegistrationRepository.findUsersByEventId(eventId, pageable);
        return userMapper.toSummaryResponseListFromProjection(registrations);
    }

    public EventRegistrationResponse registerUser(Long eventId, Long userId) {
        validate(eventId, userId);

        User user = userService.getById(userId);
        Event event = eventService.getById(eventId);

        EventRegistration eventRegistration = new EventRegistration(user, event);
        EventRegistration savedEventRegistration = eventRegistrationRepository.save(eventRegistration);

        return eventRegistrationMapper.toEventRegistrationResponse(savedEventRegistration);
    }

    private void validate(Long eventId, Long userId) {
        if (eventRegistrationRepository.existsByUserIdAndEventId(userId, eventId)) {
            throw new ConflictException(
                    "User with id %s already registered for event with id %s".formatted(userId, eventId)
            );
        }

        Event event = eventService.getById(eventId);
        if (event.getRegistrations().size() >= event.getMaxParticipants()) {
            throw new ConflictException("Maximum number of participants exceeded");
        }
        if (event.getEventTime().isBefore(LocalDateTime.now())) {
            throw new ConflictException("Event with id " + event.getId() + " is no more relevant");
        }
    }

}
