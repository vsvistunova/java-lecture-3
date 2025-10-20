package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.model.Event;
import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.util.TestDataUtil;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/events")
public class EventController {

    private final List<Event> events = TestDataUtil.createTestEvents();
    private final List<User> users = TestDataUtil.createTestUsers();

    @GetMapping("/{eventId}")
    private Event getEventById(@PathVariable Long eventId) {
        for (Event currentEvent : events) {
            if (currentEvent.getId().equals(eventId)) {
                return currentEvent;
            }
        }
        return null;
    }

    @GetMapping
    public List<Event> getEvents() {
        return events;
    }

    @PostMapping
    public Event createEvent(@RequestBody Event newEvent) {
        if (newEvent.getEventTime().isBefore(LocalDateTime.now()) ||
                newEvent.getDescription().isBlank() ||
                newEvent.getTitle().isBlank() ||
                newEvent.getLocation().isBlank() ||
                newEvent.getMaxRegistrations() <= 0) {
            return null;
        }

        boolean unique = false;
        while (!unique) {
            Long newId = Math.abs(new Random().nextLong());
            unique = events.stream()
                    .noneMatch(event -> event.getId() == newId);
            if (unique) {
                newEvent.setId(newId);
            }
        }

        events.add(newEvent);
        return newEvent;
    }

    @PutMapping("/{eventId}")
    public Event updateEvent(@PathVariable Long eventId, @RequestBody Event updatedEvent) {
        if (updatedEvent.getEventTime().isBefore(LocalDateTime.now()) ||
                updatedEvent.getDescription().isBlank() ||
                updatedEvent.getTitle().isBlank() ||
                updatedEvent.getLocation().isBlank() ||
                updatedEvent.getMaxRegistrations() <= 0) {
            return null;
        }
        Event currentEvent = getEventById(eventId);

        if (currentEvent != null) {
            currentEvent.setEventTime(updatedEvent.getEventTime());
            currentEvent.setDescription(updatedEvent.getDescription());
            currentEvent.setTitle(updatedEvent.getTitle());
            currentEvent.setLocation(updatedEvent.getLocation());
            currentEvent.setMaxRegistrations(updatedEvent.getMaxRegistrations());
            currentEvent.setUsers(updatedEvent.getUsers());
            return updatedEvent;
        }

        return null;
    }

    @DeleteMapping("/{eventId}")
    public boolean deleteEvent(@PathVariable Long eventId) {
        Event eventToDelete = getEventById(eventId);

        if (eventToDelete == null) {
            return false;
        } else {
            events.remove(eventToDelete);
            return true;
        }
    }

    @GetMapping("{eventId}/is-event-passed")
    public boolean isEventPassed(@PathVariable Long eventId) {
        Event eventToCheck = getEventById(eventId);

        if (eventToCheck == null ||
                eventToCheck.getEventTime().isBefore(LocalDateTime.now())) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/{eventId}/record/{userId}")
    public boolean recordUser(@PathVariable Long eventId, @PathVariable Long userId) {
        Event event = getEventById(eventId);
        if (event == null ||
                event.getEventTime().isBefore(LocalDateTime.now()) ||
                event.getUsers().size() == event.getMaxRegistrations() ||
                event.getUsers().stream().anyMatch(u -> u.getId().equals(userId))) {
            return false;
        }
        User userToRecord = null;
        for (User user : users) {
            if (user.getId().equals(userId)) {
                userToRecord = user;
                break;
            }
        }
        if (userToRecord == null) {
            return false;
        } else {
            event.getUsers().add(userToRecord);
            return true;
        }
    }

    @DeleteMapping("/{eventId}/record/{userId}")
    public boolean deleteUserRecord(@PathVariable Long eventId, @PathVariable Long userId) {
        Event event = getEventById(eventId);
        if (event == null) {
            return false;
        }
        User userToDelete = null;
        for (User user : event.getUsers()) {
            if (user.getId().equals(userId)) {
                userToDelete = user;
                break;
            }
        }
        if (userToDelete == null) {
            return false;
        } else {
            event.getUsers().remove(userToDelete);
            return true;
        }
    }

    @GetMapping("/filter")
    public List<Event> getFilteredEvents(
            @RequestParam(required = false) LocalDateTime minDate,
            @RequestParam(required = false) LocalDateTime maxDate,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) boolean onlyAvailableRegistrations,
            @RequestParam(required = false) boolean onlyCurrentEvents,
            @RequestParam(required = false) Long userId) {
        return events.stream()
                .filter(event -> minDate == null || event.getEventTime().isAfter(minDate) || event.getEventTime().isEqual(minDate))
                .filter(event -> maxDate == null || event.getEventTime().isBefore(maxDate) || event.getEventTime().isEqual(maxDate))
                .filter(event -> location == null || event.getLocation().equals(location))
                .filter(event -> !onlyAvailableRegistrations || event.getUsers().size() != event.getMaxRegistrations())
                .filter(event -> !onlyCurrentEvents || event.getEventTime().isAfter(LocalDateTime.now()))
                .filter(event -> userId == null || event.getUsers().stream().anyMatch(u -> u.getId().equals(userId)))
                .toList();
    }
}
