package com.university.java_lecture_3.service;

import com.university.java_lecture_3.exception.EventValidationException;
import com.university.java_lecture_3.model.Event;
import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.util.IdGeneratorUtil;
import com.university.java_lecture_3.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final List<Event> events;

    private final UserService userService;
    private final Validator<Event, EventValidationException> validator;

    @Autowired
    public EventService(UserService userService, Validator<Event, EventValidationException> validator) {
        this.events = new ArrayList<>();
        this.userService = userService;
        this.validator = validator;
        createTestEvents();
    }

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
                () -> new RuntimeException("Event with id " + id + " not found")
        );
        return events.contains(event) && LocalDateTime.now().isBefore(event.getDateTime());
    }

    public Event registerUser(Long eventId, Long userId) {
        Event event = findById(eventId).orElseThrow(
                () -> new RuntimeException("Event with id " + eventId + " not found")
        );
        User user = userService.findById(userId).orElseThrow(
                () -> new RuntimeException("User with id " + userId + " not found")
        );

        validator.validate(event);
        if (event.getSignedUpUsers().contains(user)) {
            throw new EventValidationException("User with id " + userId + " is already signed up");
        }

        event.getSignedUpUsers().add(user);

        return event;
    }

    public Optional<Event> findById(Long id) {
        for (Event event : events) {
            if (event.getId().equals(id)) {
                return Optional.of(event);
            }
        }

        return Optional.empty();
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
                () -> new RuntimeException("Event with id " + id + " not found")
        );
        updatedEvent.setName(event.getName());
        updatedEvent.setDescription(event.getDescription());
        updatedEvent.setDateTime(event.getDateTime());
        updatedEvent.setLocation(event.getLocation());
        updatedEvent.setMaxNumOfPeople(event.getMaxNumOfPeople());
        updatedEvent.setSignedUpUsers(event.getSignedUpUsers());

        return updatedEvent;
    }

    public boolean delete(Long id) {
        if (findById(id).isPresent()) {
            events.remove(findById(id).get());
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

        int NO_LIMIT = 0;
        if (event.getMaxNumOfPeople() == NO_LIMIT) {
            return true;
        }

        int currentParticipants = event.getSignedUpUsers().size();
        return currentParticipants < event.getMaxNumOfPeople();
    }

    private boolean filterByUpcoming(Event event, Boolean onlyUpcoming) {
        if (onlyUpcoming == null || !onlyUpcoming) {
            return true;
        }
        return event.getDateTime().isAfter(LocalDateTime.now());
    }

    private void createTestEvents() {
        save(new Event("Конференция по Java", "Ежегодная конференция Java разработчиков",
                LocalDateTime.of(2025, 3, 15, 10, 0), "Онлайн"));
        save(new Event("Воркшоп по Spring Boot", "Практический воркшоп по Spring Boot",
                LocalDateTime.of(2025, 4, 20, 14, 0), "Онлайн"));
        save(new Event("Митап по микросервисам", "Обсуждение архитектуры микросервисов",
                LocalDateTime.of(2025, 5, 10, 18, 30), "315", 30));
        save(new Event("Hackathon 2025", "48-часовой хакатон",
                LocalDateTime.of(2025, 6, 1, 9, 0), "Онлайн"));
        save(new Event("Доклад про AI", "Современные тенденции в искусственном интеллекте",
                LocalDateTime.of(2025, 2, 28, 16, 0), "305а", 120));
        save(new Event("SQL мастер-класс", "Продвинутые техники работы с базами данных",
                LocalDateTime.of(2025, 7, 12, 11, 0), "312", 25));
        save(new Event("Frontend митап", "React vs Angular: что выбрать в 2025",
                LocalDateTime.of(2025, 8, 5, 19, 0), "402", 20));
        save(new Event("DevOps конференция", "Автоматизация и CI/CD",
                LocalDateTime.of(2025, 9, 15, 10, 0), "507а", 30));
        save(new Event("Карьерный воркшоп", "Как развиваться в IT",
                LocalDateTime.of(2025, 10, 20, 15, 0), "312", 3));
        save(new Event("Новогодний митап", "Итоги года в IT",
                LocalDateTime.of(2025, 12, 15, 18, 0), "Онлайн"));
    }

}
