package com.example.java_lecture_3.service;

import com.example.java_lecture_3.model.Event;
import com.example.java_lecture_3.model.User;
import com.example.java_lecture_3.util.TestDataUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private final List<Event> events = TestDataUtil.createTestEvents();

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

    public Event createEvent(@RequestBody Event event){
        event.setId(generateId());
        event.setAttendees(new ArrayList<>());
        events.add(event);
        return event;
    }

    public Event getEventById(@PathVariable Long id) {
        return events.stream()
                .filter(event -> event.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public Event updateEvent(@PathVariable Long id, @RequestBody Event eventUpdate){
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

    public boolean deleteEvent(@PathVariable Long id){
        if (getEventById(id) == null) {
            throw new IllegalArgumentException("Event with id " + id + " not found");
        }else{
            events.removeIf(event -> event.getId().equals(id));
            return true;
        }
    }
    public boolean checkEventRelevant(@PathVariable Long id){
        Event eventById = getEventById(id);
        if (eventById == null) {
            throw new IllegalArgumentException("Event with id " + id + " not found");
        }
        return eventById.getDateTime().isAfter(LocalDateTime.now());
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
    public Event addUserToEvent(@PathVariable Long eventId, @PathVariable Long userId){
        Event event = getEventById(eventId);
        if (event == null) {
            throw new IllegalArgumentException("Event with id " + eventId + " not found");
        }

        User user = findUserById(userId);
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
    public Event removeUserFromEvent(@PathVariable Long eventId, @PathVariable Long userId){
        Event event = getEventById(eventId);
        if (event == null) {
            throw new IllegalArgumentException("Event with id " + eventId + " not found");
        }

        User user = findUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User with id " + userId + " not found");
        }

        boolean removed = event.getAttendees().removeIf(attendee -> attendee.getId().equals(userId));
        if (!removed) {
            throw new IllegalArgumentException("User was not attending this event");
        }

        return event;
    }

    private User findUserById(Long id) {
        List<User> users = TestDataUtil.createTestUsers();
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
