package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.model.entity.Event;
import com.university.java_lecture_3.service.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventsController {

    private final EventsService eventsService;

    @GetMapping
    public List<Event> getAllEvent(){return eventsService.getAllEvent();} // выводим все ивенты
//
//    @PostMapping
//    public Event createEvent(@RequestBody Event newEvent) {
//        return eventsService.createEvent(newEvent);
//    }
//
//    @GetMapping("/{eventId}")
//    public Event getEventById(@PathVariable Long eventId) {
//        return eventsService.getEventById(eventId);
//    }
//
//    @GetMapping("/{eventId}/time")
//    public boolean getEventTime(@PathVariable Long eventId){
//        return eventsService.getEventTime(eventId);
//    }
//
//    @PostMapping("/{eventId}/users/{userId}")
//    public Event addUserToEvent(@PathVariable Long eventId, @PathVariable Long userId){
//        return eventsService.addUserToEvent(eventId, userId);
//    }
//
//    @PutMapping("/{eventId}")
//    public Event updateEvent(@PathVariable Long eventId, @RequestBody Event updatedEvent) {
//        return eventsService.updateEvent(eventId,updatedEvent);
//    }
//
//    @DeleteMapping("/{eventId}")
//    public boolean deleteEvent(@PathVariable Long eventId) {
//        return eventsService.deleteEvent(eventId);
//    }
}
