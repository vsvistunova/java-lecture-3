package com.university.java_lecture_3.service;

import com.university.java_lecture_3.dto.request.EventRequest;
import com.university.java_lecture_3.dto.response.EventResponse;
import com.university.java_lecture_3.exception.NotFoundException;
import com.university.java_lecture_3.mapper.EventMapper;
import com.university.java_lecture_3.model.Event;
import com.university.java_lecture_3.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

//    public List<EventResponse> getFilteredEvents(
//            Long userId, LocalDateTime startDate, LocalDateTime endDate, String location,
//            Boolean onlyWithFreePlaces, Boolean onlyUpcoming
//    ) {
//        List<Event> filteredEvents = eventRepository.findFilteredEvents(
//                userId, startDate, endDate, location, onlyWithFreePlaces, onlyUpcoming
//        );
//
//        return eventMapper.toResponseList(filteredEvents);
//    }

    public boolean isRelevant(Long id) {
        return eventRepository.isRelevant(id);
    }

    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event with id " + id + " not found"));
    }

    public List<EventResponse> getAll(Pageable pageable) {
        List<Event> events = eventRepository.findAll(pageable).getContent();

        return eventMapper.toResponseList(events);
    }

    public EventResponse save(EventRequest eventRequest) {
        Event event = eventMapper.toEntity(eventRequest);
        Event savedEvent = eventRepository.save(event);

        return eventMapper.toResponse(savedEvent);
    }

    public void update(Long id, EventRequest eventDetails) {
        Event event = getById(id);

        event.setTitle(eventDetails.title());
        event.setDescription(eventDetails.description());
        event.setEventTime(eventDetails.eventTime());
        event.setLocation(eventDetails.location());
        event.setMaxParticipants(eventDetails.maxParticipants());

        eventRepository.save(event);
    }

    public void delete(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event with id " + id + " not found"));

        eventRepository.delete(event);
    }

}
