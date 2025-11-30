package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.dto.request.EventRequest;
import com.university.java_lecture_3.dto.response.EventResponse;
import com.university.java_lecture_3.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

//    @GetMapping("/filtered")
//    public List<EventResponse> getFilteredEvents(
//            @RequestParam(required = false) Long userId,
//            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
//            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
//            @RequestParam(required = false) String location,
//            @RequestParam(required = false) Boolean onlyWithFreePlaces,
//            @RequestParam(required = false) Boolean onlyUpcoming
//    ) {
//        return eventService.getFilteredEvents(userId, startDate, endDate, location, onlyWithFreePlaces, onlyUpcoming);
//    }

    @GetMapping("/{id}/relevance")
    public boolean isRelevant(@PathVariable Long id) {
        return eventService.isRelevant(id);
    }

    @GetMapping
    public List<EventResponse> getAll(@PageableDefault(size = 5) Pageable pageable) {
        return eventService.getAll(pageable);
    }

    @PostMapping
    public EventResponse create(@Valid @RequestBody EventRequest event) {
        return eventService.save(event);
    }

    @PutMapping("/{id}")
    public EventResponse update(@PathVariable Long id, @Valid @RequestBody EventRequest event) {
        return eventService.update(id, event);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return eventService.delete(id);
    }

}
