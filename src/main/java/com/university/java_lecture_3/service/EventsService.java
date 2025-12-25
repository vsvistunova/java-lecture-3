package com.university.java_lecture_3.service;

import com.university.java_lecture_3.model.entity.Event;
import com.university.java_lecture_3.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class  EventsService {
    private final EventRepository eventRepository; //List<Event> events = TestDataUtil.createTestEvents();
    private final UserService userService;

    public List<Event> getAllEvent(){return eventRepository.findAll();} // выводим все ивенты


//    public Event getEventById( Long eventIdToFind) {
//        return events.stream()
//                .filter(event -> event.getId().equals(eventIdToFind))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Мероприятие с id " + eventIdToFind + " не найдено"));
//    }
//
//    public boolean getEventTime(Long eventId){
//        return events.stream()
//                .filter(e -> e.getId().equals(eventId))
//                .findFirst()
//                .map(e -> {
//                    LocalDateTime now = LocalDateTime.now();
//                    LocalDateTime end = e.getEndTime();
//                    if (end == null) {
//                        return false;
//                    }
//                    if (end.isAfter(now)) {
//                        return true;
//                    }
//                    return false;
//                })
//                .orElseThrow(() -> new RuntimeException("Мероприятие с id " + eventId + " не найдено"));
//    }
//
//    public Event createEvent(Event newEvent) {
//        events.add(newEvent);
//        return newEvent;
//    }
//
//    public Event updateEvent(Long eventId,  Event updatedEvent) {
//        return events.stream()
//                .filter(e -> e.getId().equals(eventId))
//                .findFirst()
//                .map(currentEvent -> {
//                    currentEvent.setTitle(updatedEvent.getTitle());
//                    currentEvent.setDescription(updatedEvent.getDescription());
//                    currentEvent.setStartTime(updatedEvent.getStartTime());
//                    currentEvent.setEndTime(updatedEvent.getEndTime());
//                    currentEvent.setLocation(updatedEvent.getLocation());
//                    currentEvent.setUsers(updatedEvent.getUsers());
//                    return currentEvent;
//                })
//                .orElseThrow(() -> new RuntimeException("Мероприятие с id " + eventId + " не найдено"));
//    }
//
//    public boolean deleteEvent(Long eventId) {
//        Event toDelete = events.stream()
//                .filter(e -> e.getId().equals(eventId))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Мероприятие с id " + eventId + " не найдено"));
//        return events.remove(toDelete);
//    }
//
//    public Event addUserToEvent( Long eventId,  Long userId){
//        Event currentEvent = Optional.ofNullable(getEventById(eventId))
//                .orElseThrow(() -> new RuntimeException("Мероприятие с id " + eventId + " не найдено"));
//
//        LocalDateTime now = LocalDateTime.now();
//        Optional.ofNullable(currentEvent.getEndTime())
//                .filter(end -> end.isAfter(now))
//                .orElseThrow(() -> new RuntimeException("Мероприятие уже завершилось"));
//
//        List<User> registeredUsers = Optional.ofNullable(currentEvent.getUsers())
//                .orElseThrow(() -> new RuntimeException("Список участников мероприятия не инициализирован"));
//
//        Optional.of(currentEvent)
//                .filter(ev -> ev.getMaxUsers() <= 0 || registeredUsers.size() < ev.getMaxUsers())
//                .orElseThrow(() -> new RuntimeException("Места на мероприятии закончились"));
//
//        User user = userService.getUserById(userId);
//
//        Optional.of(user)
//                .filter(u -> registeredUsers.stream().noneMatch(us -> us.getId().equals(u.getId())))
//                .orElseThrow(() -> new RuntimeException("Пользователь уже записан на это мероприятие"));
//
//        registeredUsers.add(user);
//        return currentEvent;
//    }


}
