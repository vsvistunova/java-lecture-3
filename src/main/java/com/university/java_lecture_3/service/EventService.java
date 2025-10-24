package com.university.java_lecture_3.service;

import com.university.java_lecture_3.exception.EventValidationException;
import com.university.java_lecture_3.model.Event;
import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.repository.EventRepository;
import com.university.java_lecture_3.validation.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    private final UserService userService;

    @Qualifier("creationEventValidator")
    private final Validator<Event, EventValidationException> creationValidator;

    @Qualifier("registrationEventValidator")
    private final Validator<Event, EventValidationException> registrationValidator;

    public EventService(
            EventRepository eventRepository, UserService userService,
            @Qualifier("creationEventValidator") Validator<Event, EventValidationException> creationValidator,
            @Qualifier("registrationEventValidator") Validator<Event, EventValidationException> registrationValidator
    ) {
        this.eventRepository = eventRepository;
        this.userService = userService;
        this.creationValidator = creationValidator;
        this.registrationValidator = registrationValidator;
        createTestEvents();
    }

    public List<Event> getFilteredEvents(
            Long userId, LocalDateTime startDate, LocalDateTime endDate, String location,
            Boolean onlyWithFreePlaces, Boolean onlyUpcoming
    ) {
        return eventRepository.getFilteredEvents(userId, startDate, endDate, location, onlyWithFreePlaces, onlyUpcoming);
    }

    public boolean isRelevant(Long id) {
        return eventRepository.isRelevant(id);
    }

    public Event registerUser(Long eventId, Long userId) {
        Event event = findById(eventId);
        User user = userService.findById(userId);

        registrationValidator.validate(event);
        if (event.getSignedUpUsers().contains(user)) {
            throw new EventValidationException("User with id " + userId + " is already signed up");
        }

        return eventRepository.registerUser(user, event);
    }

    public Event findById(Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);

        if (optionalEvent.isEmpty()) {
            throw new EventValidationException("Event with id " + id + " not found");
        }

        return optionalEvent.get();
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event save(Event event) {
        creationValidator.validate(event);
        return eventRepository.save(event);
    }

    public Event update(Long id, Event event) {
        creationValidator.validate(event);
        return eventRepository.update(id, event);
    }

    public boolean delete(Long id) {
        return eventRepository.delete(id);
    }

    private void createTestEvents() {
        save(new Event("Конференция по Java", "Ежегодная конференция Java разработчиков",
                LocalDateTime.of(2026, 3, 15, 10, 0), "Онлайн"));
        save(new Event("Воркшоп по Spring Boot", "Практический воркшоп по Spring Boot",
                LocalDateTime.of(2026, 4, 20, 14, 0), "Онлайн"));
        save(new Event("Митап по микросервисам", "Обсуждение архитектуры микросервисов",
                LocalDateTime.of(2026, 5, 10, 18, 30), "315", 30));
        save(new Event("Hackathon 2025", "48-часовой хакатон",
                LocalDateTime.of(2026, 6, 1, 9, 0), "Онлайн"));
        save(new Event("Доклад про AI", "Современные тенденции в искусственном интеллекте",
                LocalDateTime.of(2026, 2, 28, 16, 0), "305а", 120));
        save(new Event("SQL мастер-класс", "Продвинутые техники работы с базами данных",
                LocalDateTime.of(2026, 7, 12, 11, 0), "312", 25));
        save(new Event("Frontend митап", "React vs Angular: что выбрать в 2025",
                LocalDateTime.of(2026, 8, 5, 19, 0), "402", 20));
        save(new Event("DevOps конференция", "Автоматизация и CI/CD",
                LocalDateTime.of(2026, 9, 15, 10, 0), "507а", 30));
        save(new Event("Карьерный воркшоп", "Как развиваться в IT",
                LocalDateTime.of(2025, 11, 20, 15, 0), "312", 3));
        save(new Event("Новогодний митап", "Итоги года в IT",
                LocalDateTime.of(2025, 12, 15, 18, 0), "Онлайн"));
    }

}
