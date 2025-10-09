package com.d1sxrder3d.java_spring_prfct.controller;

import com.d1sxrder3d.java_spring_prfct.model.Event;
import com.d1sxrder3d.java_spring_prfct.model.User;
import com.d1sxrder3d.java_spring_prfct.repository.EventRepository;
import com.d1sxrder3d.java_spring_prfct.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        event.getParticipants().clear();
        return eventRepository.save(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        Optional<Event> existing = eventRepository.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Event event = existing.get();
        event.setName(updatedEvent.getName());
        event.setDescription(updatedEvent.getDescription());
        event.setEventDateTime(updatedEvent.getEventDateTime());
        event.setLocation(updatedEvent.getLocation());
        event.setMaxParticipants(updatedEvent.getMaxParticipants());
        return ResponseEntity.ok(eventRepository.save(event));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (!eventRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        eventRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/upcoming")
    public ResponseEntity<Boolean> isUpcoming(@PathVariable Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        boolean upcoming = event.get().isUpcoming();
        return ResponseEntity.ok(upcoming);
    }

    @PostMapping("/{eventId}/register/{userId}")
    public ResponseEntity<String> registerUser(@PathVariable Long eventId, @PathVariable Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!event.isUpcoming()) {
            return ResponseEntity.badRequest().body("Event has already passed");
        }
        if (event.isFull()) {
            return ResponseEntity.badRequest().body("Event is full");
        }
        if (event.isUserRegistered(user)) {
            return ResponseEntity.badRequest().body("User already registered");
        }

        event.getParticipants().add(user);
        eventRepository.save(event);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/{eventId}/unregister/{userId}")
    public ResponseEntity<String> unregisterUser(@PathVariable Long eventId, @PathVariable Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!event.isUserRegistered(user)) {
            return ResponseEntity.badRequest().body("User is not registered for this event");
        }

        event.getParticipants().remove(user);
        eventRepository.save(event);
        return ResponseEntity.ok("User unregistered successfully");
    }

    @GetMapping("/filter")
    public List<Event> filterEvents(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) LocalDateTime minDate,
            @RequestParam(required = false) LocalDateTime maxDate,
            @RequestParam(required = false, defaultValue = "false") boolean onlyUpcoming,
            @RequestParam(required = false, defaultValue = "false") boolean onlyWithSpaces
    ) {
        return eventRepository.findFiltered(userId, location, minDate, maxDate, onlyUpcoming, onlyWithSpaces);
    }
}