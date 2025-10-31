package com.university.java_lecture_3.service;

import com.university.java_lecture_3.model.Event;
import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.util.TestDataUtil;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final List<Event> events = TestDataUtil.createTestEvents();
    private final UserService userService;

    public List<Event> getAllEvents(){
        return events;
    }
    private long generateId() {
        return events.stream()
                .mapToLong(Event::getId)
                .max()
                .orElse(0L)
                + 1;
    }

    public Event createEvent(Event event){
        event.setId(generateId());
        event.setAttendees(new ArrayList<>());
        events.add(event);
        return event;
    }
    public Optional<Event> findEventById(Long id) {
        return events.stream()
                .filter(event -> event.getId().equals(id))
                .findFirst();
    }
    public Event getEventById(Long id) {
        return findEventById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event with id " + id + " not found"));
    }
    public Event updateEvent(Long id, Event eventUpdate){
        Event updateEvent = getEventById(id);
        if (updateEvent != null) {
            updateEvent.setName(eventUpdate.getName());
            updateEvent.setDescription(eventUpdate.getDescription());
            updateEvent.setDateTime(eventUpdate.getDateTime());
            updateEvent.setLocation(eventUpdate.getLocation());
            updateEvent.setCapacity(eventUpdate.getCapacity());
            return updateEvent;
        }else{
            throw new IllegalArgumentException("Event with id " + id + " not found");
        }
    }

    public boolean deleteEvent( Long id){
        Optional<Event> eventOpt = findEventById(id);
        if (eventOpt.isEmpty()) {
            throw new IllegalArgumentException("Event with id " + id + " not found");
        }
        return events.remove(eventOpt.get());
    }
    public boolean checkEventRelevant(Long id){
        return findEventById(id)
                .map(event -> event.getDateTime().isAfter(LocalDateTime.now()))
                .orElseThrow(() -> new IllegalArgumentException("Event with id " + id + " not found"));
    }

    private boolean checkFreePlace(Event event) {
        if (event.getCapacity() == null) {
            return true;
        }
        return event.getAttendees().size() < event.getCapacity();
    }

    private boolean isUserAttending(Event event, User user) {
        return event.getAttendees().stream()
                .anyMatch(attendee -> attendee.getId().equals(user.getId()));
    }
    public Event addUserToEvent( Long eventId, Long userId){
        Event event = getEventById(eventId);
        User user = userService.findUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));
        if (event == null) {
            throw new IllegalArgumentException("Event with id " + eventId + " not found");
        }
        if (user == null) {
            throw new IllegalArgumentException("User with id " + userId + " not found");
        }

        if (!checkEventRelevant(eventId)) {
            throw new IllegalArgumentException("Event is not relevant anymore");
        }

        if (!checkFreePlace(event)) {
            throw new IllegalArgumentException("No free places available");
        }

        if (isUserAttending(event, user)) {
            throw new IllegalArgumentException("User is already attending this event");
        }

        event.getAttendees().add(user);
        return event;
    }
    public Event removeUserFromEvent(Long eventId, Long userId){
        Event event = getEventById(eventId);
        User user = userService.findUserById(userId) // Должен бросать исключение или возвращать Optional
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));
        if (event == null) {
            throw new IllegalArgumentException("Event with id " + eventId + " not found");
        }
        if (user == null) {
            throw new IllegalArgumentException("User with id " + userId + " not found");
        }

        boolean removed = event.getAttendees().removeIf(attendee -> attendee.getId().equals(userId));
        if (!removed) {
            throw new IllegalArgumentException("User was not attending this event");
        }
        return event;
    }
    public List<Event> findEventsWithFilters(Long userId, LocalDateTime minDate, LocalDateTime maxDate, String location, Boolean hasFreePlaces, Boolean isRelevant) {
        return events.stream()
                .filter(event -> {
                    boolean userMatch = userId == null || event.getAttendees().stream().anyMatch(a -> a.getId().equals(userId));
                    boolean relevant = isRelevant == null || checkEventRelevant(event.getId());
                    boolean freePlaces = hasFreePlaces == null || checkFreePlace(event);
                    boolean locationMatch = location == null || event.getLocation().equals(location);
                    boolean dateMatch = minDate == null || maxDate == null || event.getDateTime().isAfter(minDate) && event.getDateTime().isBefore(maxDate);
                    return  userMatch && relevant && freePlaces && locationMatch && dateMatch;
                })
                .collect(Collectors.toList());
    }
}
