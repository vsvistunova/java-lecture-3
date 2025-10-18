package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.model.Event;
import com.university.java_lecture_3.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/filtered")
    public List<Event> getFilteredEvents(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Boolean onlyWithFreePlaces,
            @RequestParam(required = false) Boolean onlyUpcoming
    ) {
        return eventService.getFilteredEvents(userId, startDate, endDate, location, onlyWithFreePlaces, onlyUpcoming);
    }

    @GetMapping("/{id}/relevance")
    public boolean isRelevant(@PathVariable Long id) {
        return eventService.isRelevant(id);
    }

    @PostMapping("/{eventId}/participants/{userId}")
    public Event addParticipant(@PathVariable Long eventId, @PathVariable Long userId) {
        return eventService.registerUser(eventId, userId);
    }

    @GetMapping
    public List<Event> getAll() {
        return eventService.findAll();
    }

    @PostMapping
    public Event create(@RequestBody Event event) {
        return eventService.save(event);
    }

    @PutMapping("/{id}")
    public Event update(@PathVariable Long id, @RequestBody Event event) {
        return eventService.update(id, event);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return eventService.delete(id);
    }

}
