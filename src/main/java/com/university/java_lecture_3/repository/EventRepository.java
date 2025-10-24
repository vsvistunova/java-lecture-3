package com.university.java_lecture_3.repository;

import com.university.java_lecture_3.exception.EventValidationException;
import com.university.java_lecture_3.model.Event;
import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.util.IdGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventRepository {

    private final List<Event> events;

    public List<Event> getFilteredEvents(
            Long userId, LocalDateTime startDate, LocalDateTime endDate, String location,
            Boolean onlyWithFreePlaces, Boolean onlyUpcoming
    ) {
        return events.stream()
                .filter(event -> filterByUserId(event, userId))
                .filter(event -> filterByDateRange(event, startDate, endDate))
                .filter(event -> filterByLocation(event, location))
                .filter(event -> filterByFreePlaces(event, onlyWithFreePlaces))
                .filter(event -> filterByUpcoming(event, onlyUpcoming))
                .toList();
    }

    public boolean isRelevant(Long id) {
        Event event = findById(id).orElseThrow(
                () -> new EventValidationException("Event with id " + id + " not found")
        );
        return events.contains(event) && LocalDateTime.now().isBefore(event.getDateTime());
    }

    public Event registerUser(User user, Event event) {
        event.registerUser(user);
        return event;
    }

    public Optional<Event> findById(Long id) {
        return events.stream()
                .filter(event -> event.getId().equals(id))
                .findFirst();
    }

    public List<Event> findAll() {
        return events;
    }

    public Event save(Event event) {
        event.setId(IdGeneratorUtil.generate(Event.class));
        events.add(event);

        return event;
    }

    public Event update(Long id, Event event) {
        Event updatedEvent = findById(id).orElseThrow(
                () -> new EventValidationException("Event with id " + id + " not found")
        );

        updatedEvent.setName(event.getName());
        updatedEvent.setDescription(event.getDescription());
        updatedEvent.setDateTime(event.getDateTime());
        updatedEvent.setLocation(event.getLocation());
        updatedEvent.setMaxNumOfUsers(event.getMaxNumOfUsers());
        updatedEvent.setSignedUpUsers(event.getSignedUpUsers());

        return updatedEvent;
    }

    public boolean delete(Long id) {
        Optional<Event> optionalEvent = findById(id);

        if (optionalEvent.isPresent()) {
            events.remove(optionalEvent.get());
            return true;
        }

        return false;
    }

    private boolean filterByUserId(Event event, Long userId) {
        if (userId == null) {
            return true;
        }
        return event.getSignedUpUsers().stream()
                .anyMatch(user -> user.getId().equals(userId));
    }

    private boolean filterByDateRange(Event event, LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null && endDate == null) {
            return true;
        }

        LocalDateTime eventDate = event.getDateTime();

        if (startDate != null && endDate != null) {
            return !eventDate.isBefore(startDate) && !eventDate.isAfter(endDate);
        } else if (startDate != null) {
            return !eventDate.isBefore(startDate);
        } else {
            return !eventDate.isAfter(endDate);
        }
    }

    private boolean filterByLocation(Event event, String location) {
        if (location == null || location.isBlank()) {
            return true;
        }
        return event.getLocation().toLowerCase().contains(location.toLowerCase());
    }

    private boolean filterByFreePlaces(Event event, Boolean onlyWithFreePlaces) {
        if (onlyWithFreePlaces == null || !onlyWithFreePlaces) {
            return true;
        }

        if (event.getMaxNumOfUsers() == Event.NO_LIMIT_TO_CAPACITY) {
            return true;
        }

        int currentParticipants = event.getSignedUpUsers().size();
        return currentParticipants < event.getMaxNumOfUsers();
    }

    private boolean filterByUpcoming(Event event, Boolean onlyUpcoming) {
        if (onlyUpcoming == null || !onlyUpcoming) {
            return true;
        }
        return event.getDateTime().isAfter(LocalDateTime.now());
    }

}
