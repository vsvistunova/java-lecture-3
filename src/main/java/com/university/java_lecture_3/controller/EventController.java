package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.model.Event;
import com.university.java_lecture_3.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private  final EventService eventService;

    @GetMapping
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id){
        return eventService.getEventById(id);
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event){
        return eventService.createEvent(event);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event eventUpdate){
        return eventService.updateEvent(id, eventUpdate);
    }

    @DeleteMapping("/{id}")
    public boolean deleteEvent(@PathVariable Long id){
        return eventService.deleteEvent(id);
    }

    @GetMapping("/{id}/relevant")
    public boolean checkEventRelevant(@PathVariable Long id){
        return eventService.checkEventRelevant(id);
    }

    @PostMapping("/{id}/attendees/{userId}")
    public Event addUserToEvent(@PathVariable Long id, @PathVariable Long userId){
        return eventService.addUserToEvent(id, userId);
    }

    @DeleteMapping("/{id}/attendees/{userId}")
    public Event removeUserFromEvent(@PathVariable Long id, @PathVariable Long userId){
        return eventService.removeUserFromEvent(id, userId);
    }
    @GetMapping("/filtered")
    public ResponseEntity<List<Event>> getFilteredEvents(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime minDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime maxDate,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Boolean hasFreePlaces,
            @RequestParam(required = false) Boolean isRelevant
    ) {
        List<Event> filteredEvents = eventService.findEventsWithFilters(userId, minDate, maxDate, location, hasFreePlaces, isRelevant);
        return ResponseEntity.ok(filteredEvents);
    }
}