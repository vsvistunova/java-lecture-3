package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.model.Event;
import com.university.java_lecture_3.util.TestDataUtil;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventsController {

    private final List<Event> events = TestDataUtil.createTestEvents();

    @GetMapping
    public List<Event> getAllEvent(){return events;} // выводим все ивенты


    @GetMapping("/{eventIdToFind}")
    public Event getEventById(@PathVariable Long eventIdToFind) {
        for (Event event : events) {
            if (event.getId().equals(eventIdToFind)) {
                return event;
            }
        }
    return null; // не нашли ивент с таким id
    }

    @GetMapping("/{eventId}")
    public String getEventTime(@PathVariable Long eventId){


        for (Event currentEvent : events) {
            if (currentEvent.getId().equals(eventId)) {
                LocalDateTime currentTime =  LocalDateTime.now(); // узнаем текущее время
                LocalDateTime eventTime = currentEvent.getEndTime(); // смотрим время ивента
               if (eventTime.isBefore(currentTime)){
                   return "событие прошло";
               }else if (eventTime.isAfter(currentTime)){
                    return "событие ещё не началось";
               }else {
                   return "событие идет";
               }
            }
        }
        return "событие не найдено"; // не нашли ивент
    }


    @PostMapping
    public Event createEvent(@RequestBody Event newEvent) {
        events.add(newEvent);
        return newEvent;
    }

    @PutMapping("/{eventId}")
    public Event updateEvent(@PathVariable Long eventId, @RequestBody Event updatedEvent) {
        // находим ивент по id
        for (Event currentEvent : events) {
            if (currentEvent.getId().equals(eventId)) {
                // нашли ивент - обновляем
                currentEvent.setTitle(updatedEvent.getTitle());
                currentEvent.setDescription(updatedEvent.getDescription());
                currentEvent.setStartTime(updatedEvent.getStartTime());
                currentEvent.setEndTime(updatedEvent.getEndTime());
                currentEvent.setLocation(updatedEvent.getLocation());
                currentEvent.setUsers(updatedEvent.getUsers());
                return currentEvent;
            }
        }

        return null; // не нашли ивент
    }

    @DeleteMapping("/{eventId}")
    public boolean deleteEvent(@PathVariable Long eventId) {
        // используем метод, который написали ранее, чтобы найти пользователя
        Event eventToDelete = getEventById(eventId);
        if (eventToDelete == null) {
            // пользователь не найден - удалить не можем, вернем false
            return false;
        } else {
            events.remove(eventToDelete);
            return true;
        }
    }
}